import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;

public class TVMazeService {
    private static final String SEARCH_URL = "https://api.tvmaze.com/search/shows?q=";
    private static final String SHOW_URL   = "https://api.tvmaze.com/shows/";

    private final HttpClient http;

    public TVMazeService() {
        this.http = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    }

    public List<Show> searchShows(String query) throws TVTrackerException {
        String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String body = doGet(SEARCH_URL + encoded);
        return parseSearchResponse(body);
    }

    public Show fetchShowById(int id) throws TVTrackerException {
        String body = doGet(SHOW_URL + id);
        Map<String, Object> map = MiniJson.parseObject(body);
        return parseShow(map);
    }

    private List<Show> parseSearchResponse(String json) throws TVTrackerException {
        List<Object> results = MiniJson.parseArray(json);
        List<Show> shows = new ArrayList<>();
        for (Object item : results) {
            if (item instanceof Map<?, ?> entry) {
                Object showObj = entry.get("show");
                if (showObj instanceof Map<?, ?> showMap) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> typed = (Map<String, Object>) showMap;
                    shows.add(parseShow(typed));
                }
            }
        }
        return shows;
    }

    @SuppressWarnings("unchecked")
    Show parseShow(Map<String, Object> m) {
        int    id         = toInt(m.get("id"));
        String name       = str(m.get("name"));
        String language   = str(m.get("language"), "N/A");
        String genres     = extractGenres(m);
        double rating     = extractRating(m);
        String status     = str(m.get("status"), "N/A");
        String premiered  = str(m.get("premiered"));
        String ended      = str(m.get("ended"));
        String network    = extractNetwork(m);
        String summary    = Show.stripHtml(str(m.get("summary")));
        return new Show(id, name, language, genres, rating, status, premiered, ended, network, summary);
    }

    @SuppressWarnings("unchecked")
    private String extractGenres(Map<String, Object> m) {
        Object obj = m.get("genres");
        if (!(obj instanceof List<?> list)) return "";
        StringBuilder sb = new StringBuilder();
        for (Object g : list) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(g);
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private double extractRating(Map<String, Object> m) {
        Object obj = m.get("rating");
        if (obj instanceof Map<?, ?> ratingMap) {
            Object avg = ratingMap.get("average");
            if (avg instanceof Number n) return n.doubleValue();
        }
        return 0.0;
    }

    @SuppressWarnings("unchecked")
    private String extractNetwork(Map<String, Object> m) {
        Object obj = m.get("network");
        if (obj instanceof Map<?, ?> net) {
            Object name = net.get("name");
            if (name != null) return name.toString();
        }
        Object web = m.get("webChannel");
        if (web instanceof Map<?, ?> ch) {
            Object name = ch.get("name");
            if (name != null) return name.toString();
        }
        return "";
    }

    private String doGet(String url) throws TVTrackerException {
        try {
            HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(15))
                .GET()
                .build();
            HttpResponse<String> response =
                http.send(req, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            if (response.statusCode() == 200) return response.body();
            throw new TVTrackerException("API retornou HTTP " + response.statusCode(), response.statusCode());
        } catch (java.net.http.HttpTimeoutException e) {
            throw new TVTrackerException("Tempo de conexão esgotado. Verifique sua internet.", 504);
        } catch (TVTrackerException e) {
            throw e;
        } catch (Exception e) {
            throw new TVTrackerException("Erro de rede: " + e.getMessage(), 503);
        }
    }

    private int toInt(Object obj) {
        if (obj instanceof Number n) return n.intValue();
        return 0;
    }

    private String str(Object obj) {
        return str(obj, "");
    }

    private String str(Object obj, String defaultValue) {
        if (obj == null) return defaultValue;
        return obj.toString();
    }
}
