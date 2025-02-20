package utils;

import javax.swing.*;
import java.awt.*;

public class Centralizador extends JPanel {

    public Centralizador(JComponent componente) {
        setLayout(new GridLayout(1, 1));
        add(componente);
    }
}
