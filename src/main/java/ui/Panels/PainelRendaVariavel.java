package ui.Panels;

import java.awt.*;
import javax.swing.*;

public class PainelRendaVariavel extends PainelAcoes {

    public PainelRendaVariavel() {
        inicializar();
    }

    private void inicializar() {
        add(new JLabel("Painel Renda Variavel"), BorderLayout.CENTER);
    }
}
