package ui.Frames;

import java.awt.*;
import javax.swing.*;

import ui.Panels.PainelAutenticacao;
import ui.Panels.PainelLogin;
import ui.Panels.PainelConfirmacaoSenha;

import ui.Enum.TipoTelaAutenticacaoEnum;

import utils.Centralizador;

public class TelaLogin extends JFrame {

    private final PainelAutenticacao painelAutenticacao;

    public TelaLogin(TipoTelaAutenticacaoEnum tipo) {
        setTitle("Tela de Login");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 253, 248));
        setLayout(new BorderLayout());

        if (tipo == TipoTelaAutenticacaoEnum.LOGIN) {
            painelAutenticacao = new PainelLogin();
        } else {
            painelAutenticacao = new PainelConfirmacaoSenha();
        }

        add(new Centralizador(painelAutenticacao), BorderLayout.CENTER);

        setVisible(true);
    }
}
