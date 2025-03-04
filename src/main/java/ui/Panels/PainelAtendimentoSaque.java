package ui.Panels;

import java.awt.*;
import javax.swing.*;

public class PainelAtendimentoSaque extends PainelAcoes {

    public PainelAtendimentoSaque() {
        inicializar();
    }

    private void inicializar() {
        add(new JLabel("Painel Atendimento Saque"), BorderLayout.CENTER);
    }
}
