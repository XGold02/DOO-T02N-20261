import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame {
    private JTextField txtNum1, txtNum2, txtResultado;
    private JTextArea areaHistorico;
    private CalculadoraLogica logica;

    public CalculadoraGUI() {
        logica = new CalculadoraLogica();
        configurarJanela();
    }

    private void configurarJanela() {
        setTitle("Calculadora");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelEntrada = new JPanel(new GridLayout(3, 2, 5, 5));
        txtNum1 = new JTextField();
        txtNum2 = new JTextField();
        txtResultado = new JTextField();
        txtResultado.setEditable(false);

        painelEntrada.add(new JLabel(" Número 1:"));
        painelEntrada.add(txtNum1);
        painelEntrada.add(new JLabel(" Número 2:"));
        painelEntrada.add(txtNum2);
        painelEntrada.add(new JLabel(" Resultado:"));
        painelEntrada.add(txtResultado);

        JPanel painelBotoes = new JPanel(new GridLayout(1, 4, 5, 5));
        String[] operacoes = { "+", "-", "×", "÷" };

        for (String op : operacoes) {
            JButton btn = new JButton(op);
            btn.addActionListener(new BotaoHandler(op));
            painelBotoes.add(btn);
        }

        areaHistorico = new JTextArea(10, 30);
        areaHistorico.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaHistorico);
        scroll.setBorder(BorderFactory.createTitledBorder("Histórico de Operações"));

        add(painelEntrada, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class BotaoHandler implements ActionListener {
        private String operacao;

        public BotaoHandler(String operacao) {
            this.operacao = operacao;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double n1 = Double.parseDouble(txtNum1.getText().replace(",", "."));
                double n2 = Double.parseDouble(txtNum2.getText().replace(",", "."));

                double res = logica.calcular(n1, n2, operacao);
                txtResultado.setText(String.valueOf(res));

                atualizarHistorico();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erro: Digite apenas números válidos!", "Entrada Inválida",
                        JOptionPane.ERROR_MESSAGE);
            } catch (CalculadoraException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro de Cálculo", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void atualizarHistorico() {
        StringBuilder sb = new StringBuilder();
        for (String linha : logica.getHistorico()) {
            sb.append(linha).append("\n");
        }
        areaHistorico.setText(sb.toString());
    }

    public static void main(String[] args) {
        new CalculadoraGUI();
    }
}