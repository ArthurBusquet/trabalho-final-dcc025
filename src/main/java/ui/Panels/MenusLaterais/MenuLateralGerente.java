/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package ui.Panels.MenusLaterais;

import ui.Panels.MenusLaterais.MenuLateral;
import ui.Controllers.GerenciadorPainel;
import ui.Controllers.GerenciadorTela;

public class MenuLateralGerente extends MenuLateral {

    public MenuLateralGerente(GerenciadorTela controlador, GerenciadorPainel gerenciadorPainel) {
        super(controlador, gerenciadorPainel);

        textoSaudacao.setText("Olá, gerente!");

        adicionarBotao("Atendimento de saque", "AtendimentoSaque");
        adicionarBotao("Aprovar Transferencias", "AprovacaoTransferencia");
        adicionarBotao("Avaliar Solicitações de Crédito", "AvaliacaoCredito");
    }
}
