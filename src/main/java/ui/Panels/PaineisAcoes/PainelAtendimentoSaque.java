package ui.Panels.PaineisAcoes;

import java.awt.*;
import javax.swing.*;

public class PainelAtendimentoSaque extends PainelAcoes {

    public PainelAtendimentoSaque() {
        super();
        inicializar();
    }

    private void inicializar() {
        setLayout(new GridBagLayout());
        add(new JLabel("Painel Atendimento Saque"));
    }
}
