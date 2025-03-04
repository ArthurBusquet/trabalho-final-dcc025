package ui.Panels;

import java.awt.*;
import javax.swing.*;

public class PainelRendaFixa extends PainelAcoes {

    public PainelRendaFixa() {
        inicializar();
    }

    private void inicializar() {
        add(new JLabel("Painel Renda Fixa"), BorderLayout.CENTER);
    }
}
