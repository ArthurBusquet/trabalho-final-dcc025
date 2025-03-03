package ui.Frames;

import infrastructure.GerenciadorUsuarios;
import java.awt.*;
import javax.swing.*;

import ui.Panels.PainelAutenticacao;
import ui.Panels.PainelLogin;
import ui.Panels.PainelConfirmacaoSenha;

import ui.Enum.TipoTelaAutenticacaoEnum;
import domain.Entities.Usuarios.Usuario;

import utils.Centralizador;

public class TelaLogin extends JFrame {

    private final PainelAutenticacao painelAutenticacao;

    public interface LoginListener {
        void onLogin(Usuario usuario);
    }

    public TelaLogin(GerenciadorUsuarios gerenciador, TipoTelaAutenticacaoEnum tipo, LoginListener loginListener) {
        setTitle("Tela de Login");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 253, 248));
        setLayout(new BorderLayout());

        if (tipo == TipoTelaAutenticacaoEnum.LOGIN) {
            painelAutenticacao = new PainelLogin(gerenciador, loginListener); // Passa o callback
        } else {
            painelAutenticacao = new PainelConfirmacaoSenha();
        }

        add(new Centralizador(painelAutenticacao), BorderLayout.CENTER);
    }
}
