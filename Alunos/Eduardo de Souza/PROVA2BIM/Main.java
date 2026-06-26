package provaDOO;

import javax.swing.*;

import provaDOO.view.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            TelaInicial telaInicial = new TelaInicial();
            telaInicial.setVisible(true);
        });
    }
}