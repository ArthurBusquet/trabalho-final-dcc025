package ui.Frames;

import infrastructure.GerenciadorUsuarios;
import java.awt.*;
import javax.swing.*;

import ui.Controllers.GerenciadorTela;

import ui.Panels.PainelAutenticacao;
import ui.Panels.PainelLogin;
import ui.Panels.PainelConfirmacaoSenha;

import ui.Enum.TipoTelaAutenticacaoEnum;
import domain.Entities.Usuarios.Usuario;

import utils.Centralizador;

public class TelaLogin extends JFrame {

    private final PainelAutenticacao painelAutenticacao;

    protected GerenciadorTela controlador;

    public TelaLogin(GerenciadorTela controlador, TipoTelaAutenticacaoEnum tipo) {
        this.controlador = controlador;
        setTitle("Tela de Login");
        setSize(1800, 1800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 253, 248));
        setLayout(new BorderLayout());

        if (tipo == TipoTelaAutenticacaoEnum.LOGIN) {
            painelAutenticacao = new PainelLogin(controlador);
        } else {
            painelAutenticacao = new PainelConfirmacaoSenha(controlador);
        }

        add(new Centralizador(painelAutenticacao), BorderLayout.CENTER);
    }
}
