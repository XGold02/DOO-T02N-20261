package provaDOO.view;

import provaDOO.services.UsuarioService;

import javax.swing.*;
import java.awt.*;

public class TelaCadastro extends JFrame {

    private JTextField txtNome;

    private final UsuarioService usuarioService =
            new UsuarioService();

    public TelaCadastro() {

        setTitle("Cadastro - Minhas Séries");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        criarComponentes();
    }

    private void criarComponentes() {

        JPanel painel = new JPanel();
        painel.setLayout(new GridBagLayout());
        painel.setBackground(new Color(18, 18, 18));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblNome.setForeground(Color.WHITE);

        txtNome = new JTextField(20);
        txtNome.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCadastrar.setBackground(new Color(229, 9, 20));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.setBorderPainted(false);
        btnCadastrar.setOpaque(true);
        btnCadastrar.setPreferredSize(new Dimension(180, 45));

        btnCadastrar.addActionListener(
                e -> cadastrarUsuario()
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(lblNome, gbc);

        gbc.gridy = 1;
        painel.add(txtNome, gbc);

        gbc.gridy = 2;
        painel.add(btnCadastrar, gbc);

        add(painel);
    }

    private void cadastrarUsuario() {

        try {

            usuarioService.cadastrar(
                    txtNome.getText()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Usuário cadastrado com sucesso!"
            );

            dispose();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}