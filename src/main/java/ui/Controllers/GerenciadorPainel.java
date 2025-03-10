/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package ui.Controllers;

import javax.swing.*;
import java.awt.*;

import ui.Panels.PaineisAcoes.PainelAcoes;

public class GerenciadorPainel {

    private final JPanel painelAcoes;
    private final CardLayout cardLayout;

    public GerenciadorPainel(JPanel painelAcoes) {
        this.painelAcoes = painelAcoes;
        this.cardLayout = (CardLayout) painelAcoes.getLayout();
    }

    public void adicionarPainel(String nomePainel, PainelAcoes painel) {
        painelAcoes.add(painel, nomePainel);
    }

    public void mostrarPainel(String nomePainel) {
        cardLayout.show(painelAcoes, nomePainel);
    }
}
