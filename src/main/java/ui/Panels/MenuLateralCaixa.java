package ui.Panels;

import ui.Controllers.GerenciadorPainel;
import ui.Controllers.GerenciadorTela;

public class MenuLateralCaixa extends MenuLateral {

    public MenuLateralCaixa(GerenciadorTela controlador, GerenciadorPainel gerenciadorPainel) {
        super(controlador, gerenciadorPainel);
        adicionarBotao("Atendimento de saque", "AtendimentoSaque");
        adicionarBotao("Processamento de depósitos", "ProcessamentoDepositos");
    }
}
