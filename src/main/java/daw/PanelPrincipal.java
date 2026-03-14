package daw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelPrincipal extends JPanel implements ActionListener {

    private PanelBotones botonera;
    private JTextArea areaTexto;

    private double operando1 = 0;
    private String operador = "";

    public PanelPrincipal() {
        initComponents();
    }

    private void initComponents() {

        botonera = new PanelBotones();

        areaTexto = new JTextArea(3, 20);
        areaTexto.setEditable(false);
        areaTexto.setBackground(Color.WHITE);

        this.setLayout(new BorderLayout());

        this.add(areaTexto, BorderLayout.NORTH);
        this.add(botonera, BorderLayout.SOUTH);

        // añadir eventos a todos los botones
        for (JButton boton : botonera.getgrupoBotones()) {
            boton.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        JButton boton = (JButton) ae.getSource();
        String texto = boton.getText();

        if (texto.matches("[0-9]")) {
            areaTexto.append(texto);
        }

        else if (texto.matches("[+\\-*/]")) {
            operando1 = Double.parseDouble(areaTexto.getText());
            operador = texto;
            areaTexto.setText("");
        }

        else if (texto.equals("=")) {

            double operando2 = Double.parseDouble(areaTexto.getText());
            double resultado = 0;

            switch (operador) {
                case "+":
                    resultado = operando1 + operando2;
                    break;
                case "-":
                    resultado = operando1 - operando2;
                    break;
                case "*":
                    resultado = operando1 * operando2;
                    break;
                case "/":
                    resultado = operando1 / operando2;
                    break;
            }

            areaTexto.setText(Double.toString(resultado));
        }

        else if (texto.equals("C")) {
            areaTexto.setText("");
            operando1 = 0;
            operador = "";
        }
    }
}
