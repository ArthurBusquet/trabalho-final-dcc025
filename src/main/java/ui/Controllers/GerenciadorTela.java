package ui.Controllers;

import javax.swing.*;

import domain.Enum.TipoUsuarioEnum;
import infrastructure.GerenciadorUsuarios;
import static ui.Enum.TipoTelaAutenticacaoEnum.*;

import ui.Frames.TelaLogin;
import ui.Frames.TelaPrincipal;

public class GerenciadorTela {

    private JFrame telaAtual;

    public void mostrarTelaLogin() {
        if (telaAtual != null) {
            telaAtual.dispose();
        }
        telaAtual = new TelaLogin(new GerenciadorUsuarios(), this, LOGIN);
        telaAtual.setVisible(true);
    }

    public void mostrarTelaPrincipal(TipoUsuarioEnum tipoUsuario) {
        if (telaAtual != null) {
            telaAtual.dispose();
        }
        telaAtual = new TelaPrincipal(this, tipoUsuario);
        telaAtual.setVisible(true);
    }
}
