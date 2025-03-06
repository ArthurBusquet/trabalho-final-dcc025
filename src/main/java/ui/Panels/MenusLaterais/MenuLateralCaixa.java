package ui.Panels.MenusLaterais;

import ui.Panels.MenusLaterais.MenuLateral;
import ui.Controllers.GerenciadorPainel;
import ui.Controllers.GerenciadorTela;

public class MenuLateralCaixa extends MenuLateral {

    public MenuLateralCaixa(GerenciadorTela controlador, GerenciadorPainel gerenciadorPainel) {
        super(controlador, gerenciadorPainel);

        textoSaudacao.setText("Olá, caixa!");
        
        adicionarBotao("Atendimento de saque", "AtendimentoSaque");
        adicionarBotao("Processamento de depósitos", "ProcessamentoDeposito");
    }
}
