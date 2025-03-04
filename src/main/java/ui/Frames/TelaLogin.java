package ui.Frames;

import infrastructure.GerenciadorUsuarios;
import java.awt.*;
import javax.swing.*;

import ui.Controllers.GerenciadorTela;

import ui.Panels.PainelAutenticacao;
import ui.Panels.PainelLogin;
import ui.Panels.PainelConfirmacaoSenha;

import ui.Enum.TipoTelaAutenticacaoEnum;

import utils.Centralizador;

public class TelaLogin extends JFrame {

    private final PainelAutenticacao painelAutenticacao;
    protected GerenciadorTela controlador;
    private final GerenciadorUsuarios gerenciadorUsuarios;

    public TelaLogin(GerenciadorTela controlador, TipoTelaAutenticacaoEnum tipo) {
        this.controlador = controlador;
        this.gerenciadorUsuarios = new GerenciadorUsuarios();

        setTitle("Tela de Login");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 253, 248));
        setLayout(new BorderLayout());

        if (tipo == TipoTelaAutenticacaoEnum.LOGIN) {
            painelAutenticacao = new PainelLogin(gerenciadorUsuarios, controlador);
        } else {
            painelAutenticacao = new PainelConfirmacaoSenha(controlador);
        }

        add(new Centralizador(painelAutenticacao), BorderLayout.CENTER);
    }
}
