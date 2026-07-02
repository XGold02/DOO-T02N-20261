package provaDOO.view;

import provaDOO.model.Usuario;
import provaDOO.services.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaLogin extends JFrame {

    private final UsuarioService usuarioService =
            new UsuarioService();

    private List<Usuario> usuarios;

    private int indiceAtual = 0;

    private JLabel lblNome;
    private JLabel lblAvatar;

    public TelaLogin() {

        usuarios = usuarioService.listarUsuarios();

        configurarJanela();
        criarComponentes();
    }

    private void configurarJanela() {

        setTitle("Login - Minhas Séries");
        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void criarComponentes() {

        if (usuarios.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum usuário cadastrado."
            );

            new TelaInicial().setVisible(true);

            dispose();

            return;
        }

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(new Color(18, 18, 18));

        JPanel painelTopo = criarTopo();

        JPanel painelCentro = criarCarrossel();

        JPanel painelSul = criarRodape();

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);
        painelPrincipal.add(painelCentro, BorderLayout.CENTER);
        painelPrincipal.add(painelSul, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private JPanel criarTopo() {

        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setBackground(new Color(18, 18, 18));
        painelTopo.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 0, 20));

        JButton btnVoltar = new JButton("←");

        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 28));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setBackground(new Color(18, 18, 18));
        btnVoltar.setBorderPainted(false);
        btnVoltar.setFocusPainted(false);

        btnVoltar.addActionListener(e -> {

            new TelaInicial().setVisible(true);
            dispose();
        });

        JLabel titulo = new JLabel("SELECIONE UMA CONTA");

        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titulo.setForeground(new Color(229, 9, 20));

        painelTopo.add(btnVoltar, BorderLayout.WEST);
        painelTopo.add(titulo, BorderLayout.CENTER);

        return painelTopo;
    }

    private JPanel criarCarrossel() {

        JPanel painelCentro = new JPanel(new GridBagLayout());
        painelCentro.setBackground(new Color(18, 18, 18));

        JPanel painelCarrossel = new JPanel(new BorderLayout());

        painelCarrossel.setPreferredSize(
                new Dimension(450, 280));

        painelCarrossel.setBackground(
                new Color(30, 30, 30));

        painelCarrossel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(229, 9, 20), 3));

        JButton btnAnterior = new JButton("<");
        JButton btnProximo = new JButton(">");

        estilizarSeta(btnAnterior);
        estilizarSeta(btnProximo);

        JPanel painelPerfil = new JPanel();

        painelPerfil.setOpaque(false);
        painelPerfil.setLayout(
                new BoxLayout(
                        painelPerfil,
                        BoxLayout.Y_AXIS));

        Usuario usuario = usuarios.get(indiceAtual);

        lblAvatar = new JLabel(
                usuario.getNome().substring(0, 1));

        lblAvatar.setForeground(Color.WHITE);
        lblAvatar.setFont(
                new Font("Segoe UI", Font.BOLD, 48));

        JPanel avatar = new JPanel(new GridBagLayout());

        avatar.setBackground(new Color(229, 9, 20));
        avatar.setPreferredSize(
                new Dimension(120, 120));
        avatar.setMaximumSize(
                new Dimension(120, 120));

        avatar.add(lblAvatar);

        lblNome = new JLabel(usuario.getNome());

        lblNome.setForeground(Color.WHITE);
        lblNome.setFont(
                new Font("Segoe UI", Font.BOLD, 26));

        avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNome.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelPerfil.add(Box.createVerticalGlue());
        painelPerfil.add(avatar);
        painelPerfil.add(Box.createVerticalStrut(20));
        painelPerfil.add(lblNome);
        painelPerfil.add(Box.createVerticalGlue());

        btnAnterior.addActionListener(e -> {

            indiceAtual--;

            if (indiceAtual < 0) {
                indiceAtual = usuarios.size() - 1;
            }

            atualizarPerfil();
        });

        btnProximo.addActionListener(e -> {

            indiceAtual++;

            if (indiceAtual >= usuarios.size()) {
                indiceAtual = 0;
            }

            atualizarPerfil();
        });

        painelCarrossel.add(btnAnterior, BorderLayout.WEST);
        painelCarrossel.add(btnProximo, BorderLayout.EAST);
        painelCarrossel.add(painelPerfil, BorderLayout.CENTER);

        painelCentro.add(painelCarrossel);

        return painelCentro;
    }

    private JPanel criarRodape() {

        JButton btnEntrar = criarBotao("Entrar",
                new Color(40, 40, 40), Color.WHITE);

        btnEntrar.setPreferredSize(
                new Dimension(220, 55));

        btnEntrar.setFont(
                new Font("Segoe UI", Font.BOLD, 20));

        btnEntrar.addActionListener(e -> entrar());

        JPanel painelSul = new JPanel();

        painelSul.setBackground(
                new Color(18, 18, 18));

        painelSul.setBorder(
                BorderFactory.createEmptyBorder(
                        0, 0, 50, 0));

        painelSul.add(btnEntrar);

        return painelSul;
    }
    
    private JButton criarBotao(String texto, Color fundo, Color textoCor) {
        JButton botao = new JButton(texto);

        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setForeground(textoCor);
        botao.setBackground(fundo);

        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botao.setPreferredSize(new Dimension(180, 50));

        return botao;
    }

    private void atualizarPerfil() {

        Usuario usuario = usuarios.get(indiceAtual);

        lblNome.setText(usuario.getNome());

        lblAvatar.setText(
                usuario.getNome().substring(0, 1));
    }

    private void estilizarSeta(JButton botao) {

        botao.setPreferredSize(
                new Dimension(80, 80));

        botao.setFont(
                new Font("Segoe UI", Font.BOLD, 40));

        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(30, 30, 30));

        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
    }
    
    private void entrar() {

        if (usuarios == null || usuarios.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum usuário selecionado."
            );

            return;
        }

        Usuario usuarioSelecionado =
                usuarios.get(indiceAtual);

        TelaHome telaHome =
                new TelaHome(usuarioSelecionado);

        telaHome.setVisible(true);

        dispose();
    }
}