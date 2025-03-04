package ui.Panels;

import java.awt.*;
import javax.swing.*;

public class PainelProcessamentoDeposito extends PainelAcoes {

    public PainelProcessamentoDeposito() {
        inicializar();
    }

    private void inicializar() {
        add(new JLabel("Painel processamento depósito"), BorderLayout.CENTER);
    }
}
