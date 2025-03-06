package ui.Frames;

import infrastructure.GerenciadorUsuarios;
import java.awt.*;
import javax.swing.*;

import ui.Controllers.GerenciadorAutenticacao;
import ui.Controllers.GerenciadorTela;

import ui.Panels.PainelAutenticacao;
import ui.Panels.PainelLogin;
import ui.Panels.PainelCadastro;
import utils.Centralizador;

public class TelaLogin extends JFrame {

    private PainelAutenticacao painelAtual;

    private final GerenciadorTela controladorTelas;
    private final GerenciadorUsuarios gerenciadorUsuarios;
    private final GerenciadorAutenticacao gerenciadorAutenticacao;

    public TelaLogin(GerenciadorTela controlador) {
        this.controladorTelas = controlador;
        this.gerenciadorUsuarios = new GerenciadorUsuarios();
        gerenciadorAutenticacao = new GerenciadorAutenticacao(this);

        setTitle("Tela de Login");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 253, 248));
        setLayout(new BorderLayout());

        mostrarPainelLogin();
    }

    public void mostrarPainelLogin() {
        if (painelAtual != null) {
            remove(painelAtual);
            revalidate();
            repaint();
        }
        painelAtual = new PainelLogin(gerenciadorUsuarios, controladorTelas, gerenciadorAutenticacao);
        add(painelAtual, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void mostrarPainelCadastro() {
        if (painelAtual != null) {
            remove(painelAtual);
            revalidate();
            repaint();
        }
        painelAtual = new PainelCadastro(gerenciadorUsuarios, controladorTelas, gerenciadorAutenticacao);
        add(painelAtual, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
