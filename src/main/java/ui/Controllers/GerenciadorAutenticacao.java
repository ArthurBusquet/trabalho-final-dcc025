/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package ui.Controllers;

import ui.Frames.TelaLogin;

public class GerenciadorAutenticacao {

    private final TelaLogin telaLogin;

    public GerenciadorAutenticacao(TelaLogin telaLogin) {
        this.telaLogin = telaLogin;
    }

    public void mostrarTelaLogin() {
        telaLogin.mostrarPainelLogin();
    }

    public void mostrarTelaCadastro() {
        telaLogin.mostrarPainelCadastro();
    }

}
