package provaDOO.view;

import provaDOO.model.Serie;
import provaDOO.model.Usuario;
import provaDOO.services.TvMazeService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaListaSeries extends JFrame {

    private final Usuario usuario;
    private final String tipoLista;
    
    private List<Serie> series =
            new ArrayList<>();

    private final TvMazeService tvMazeService =
            new TvMazeService();

    private JTable tabela;

    private javax.swing.table.DefaultTableModel model;

    public TelaListaSeries(
            Usuario usuario,
            String tipoLista) {

        this.usuario = usuario;
        this.tipoLista = tipoLista;

        configurarJanela();
        criarComponentes();
        carregarSeries();
    }
    

    private void configurarJanela() {

        setTitle(tipoLista);

        setSize(1000, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);
    }

    private void criarComponentes() {

        JPanel principal =
                new JPanel(new BorderLayout());

        principal.setBackground(
                new Color(18, 18, 18));

        JPanel painelTopo =
                new JPanel(new BorderLayout());

        painelTopo.setBackground(
                new Color(18, 18, 18));

        JLabel titulo =
                new JLabel(tipoLista);

        titulo.setForeground(
                new Color(229, 9, 20));

        titulo.setFont(
                new Font("Segoe UI", Font.BOLD, 28));

        titulo.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 10, 20));

        painelTopo.add(
                titulo,
                BorderLayout.WEST);

        principal.add(
                painelTopo,
                BorderLayout.NORTH);

        JPanel painelOrdenacao =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT));

        painelOrdenacao.setBackground(
                new Color(18, 18, 18));

        JLabel lblOrdenar =
                new JLabel("Ordenar por:");

        lblOrdenar.setForeground(
                Color.WHITE);

        String[] ordenacoes = {
                "Nome",
                "Nota",
                "Estado",
                "Estreia"
        };

        JComboBox<String> cbOrdenacao =
                new JComboBox<>(ordenacoes);

        cbOrdenacao.addActionListener(
                e -> ordenar(
                        cbOrdenacao
                                .getSelectedItem()
                                .toString()
                )
        );

        painelOrdenacao.add(lblOrdenar);
        painelOrdenacao.add(cbOrdenacao);

        principal.add(
                painelOrdenacao,
                BorderLayout.BEFORE_FIRST_LINE);

        model =
                new javax.swing.table.DefaultTableModel(
                        new Object[]{
                                "Nome",
                                "Nota",
                                "Estado",
                                "Estreia"
                        },
                        0) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        tabela =
                new JTable(model);

        tabela.setRowHeight(35);

        tabela.setFont(
                new Font("Segoe UI", Font.PLAIN, 16));

        tabela.getTableHeader()
                .setFont(
                        new Font("Segoe UI", Font.BOLD, 16));

        JScrollPane scroll =
                new JScrollPane(tabela);

        principal.add(scroll, BorderLayout.CENTER);

        add(principal);
    }
    
	private void carregarSeries() {
	
	    try {
	
	        series.clear();
	
	        List<Long> ids =
	                obterListaIds();
	
	        for (Long id : ids) {
	
	            series.add(
	                    tvMazeService
	                            .buscarSeriePorId(id));
	        }
	
	        atualizarLista();
	
	    } catch (Exception ex) {
	
	        JOptionPane.showMessageDialog(
	                this,
	                ex.getMessage()
	        );
	    }
	}
	
	private void atualizarLista() {

	    model.setRowCount(0);

	    for (Serie serie : series) {

	        model.addRow(
	                new Object[] {

	                        serie.getNome(),

	                        serie.getNota(),

	                        serie.getStatus(),

	                        serie.getEstreia()
	                }
	        );
	    }
	}

    private List<Long> obterListaIds() {

        switch (tipoLista) {

            case "Favoritos":
                return usuario.getFavoritos();

            case "Assistidas":
                return usuario.getAssistidas();

            case "Deseja Assistir":
                return usuario.getDesejaAssistir();

            default:
                return new ArrayList<>();
        }
    }
    
    private void ordenar(
            String criterio) {

        switch (criterio) {

            case "Nome":

                series.sort(

                        (s1, s2) ->

                                s1.getNome()
                                        .compareToIgnoreCase(

                                                s2.getNome()
                                        )
                );

                break;

            case "Nota":

                series.sort(

                        (s1, s2) ->

                                Double.compare(

                                        s2.getNota(),

                                        s1.getNota()
                                )
                );

                break;

            case "Status":

                series.sort(

                        (s1, s2) ->

                                s1.getStatus()
                                        .compareToIgnoreCase(

                                                s2.getStatus()
                                        )
                );

                break;

            case "Estreia":

                series.sort(

                        (s1, s2) ->

                                s1.getEstreia()
                                        .compareTo(

                                                s2.getEstreia()
                                        )
                );

                break;
        }

        atualizarLista();
    }
    
}