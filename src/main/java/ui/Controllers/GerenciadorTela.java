package ui.Controllers;

import javax.swing.*;

import application.Controllers.SessaoUsuario;

import static ui.Enum.TipoTelaAutenticacaoEnum.*;

import ui.Frames.TelaLogin;
import ui.Frames.TelaPrincipal;

public class GerenciadorTela {

    private JFrame telaAtual;
    private final SessaoUsuario sessaoUsuario;

    public GerenciadorTela() {
        this.sessaoUsuario = SessaoUsuario.getInstancia();
    }

    public void mostrarTelaLogin() {
        if (telaAtual != null) {
            telaAtual.dispose();
        }
        telaAtual = new TelaLogin(this, LOGIN);
        telaAtual.setVisible(true);
    }

    public void mostrarTelaPrincipal() {
        if (telaAtual != null) {
            telaAtual.dispose();
        }
        telaAtual = new TelaPrincipal(this, sessaoUsuario.getUsuarioLogado().getTipoUsuario());
        telaAtual.setVisible(true);
    }

    public void logout() {
        sessaoUsuario.logout();
        mostrarTelaLogin();
    }

}
