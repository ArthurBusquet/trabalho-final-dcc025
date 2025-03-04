package ui.Panels;

import java.awt.*;
import javax.swing.*;

public class PainelSolicitacaoCredito extends PainelAcoes {

    public PainelSolicitacaoCredito() {
        inicializar();
    }

    private void inicializar() {
        add(new JLabel("Painel solicitação de crédito"), BorderLayout.CENTER);
    }
}
