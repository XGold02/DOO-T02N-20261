package provaDOO.services;

import provaDOO.model.Serie;

public class SerieService {

    private final TvMazeService tvMazeService =
            new TvMazeService();

    public Serie pesquisar(String nome)
            throws Exception {

        if(nome == null || nome.isBlank()) {
            throw new Exception("Informe uma série.");
        }

        return tvMazeService.buscarSerie(nome);
    }
}