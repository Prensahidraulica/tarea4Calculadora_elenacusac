package daw;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotones extends JPanel {

    JButton[] grupoBotones = new JButton[16];

    public PanelBotones() {
        initComponents();
    }

    private void initComponents() {

        // Creación de los botones del 0 al 9
        for (int i = 0; i < 10; i++) {
            grupoBotones[i] = new JButton(Integer.toString(i));
        }

        // Añadir botones con los operadores a las últimas posiciones de la lista
        grupoBotones[10] = new JButton("+");
        grupoBotones[11] = new JButton("-");
        grupoBotones[12] = new JButton("*");
        grupoBotones[13] = new JButton("/");
        grupoBotones[14] = new JButton("=");
        grupoBotones[15] = new JButton("C");

        // Layout tipo cuadrícula 
        this.setLayout(new GridLayout(4, 4));

        for (JButton boton : grupoBotones) {
            this.add(boton);
        }
    }

    public JButton[] getgrupoBotones() {
        return grupoBotones;
    }
}