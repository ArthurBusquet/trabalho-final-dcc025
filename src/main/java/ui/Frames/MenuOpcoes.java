package ui.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MenuOpcoes extends JFrame {
    
    public MenuOpcoes(String titulo) {
        setTitle(titulo);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 1));

        configurarMenu();
    }

    protected abstract void configurarMenu();
}
