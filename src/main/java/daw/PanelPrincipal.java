package daw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelPrincipal extends JPanel implements ActionListener {

    // Creo el objeto que me crea los botones
    private PanelBotones botonera;
    private JTextArea areaTexto;

    private double operando1 = 0;
    private String operador = "";

    // Crear la ventana principal
    public PanelPrincipal() {
        initComponents();
    }

    // Inicializar los componentes que tiene la ventana principal
    private void initComponents() {

        // Crear los botones
        botonera = new PanelBotones();

        // Crear un área de texto con unas dimensiones y un color de fondo
        areaTexto = new JTextArea(3, 20);
        areaTexto.setEditable(false);
        areaTexto.setBackground(Color.WHITE);

        this.setLayout(new BorderLayout());

        // Diferenciar la pantalla donde aparecen los botones (la parte sur) con la parte del texto (la parte norte)
        this.add(areaTexto, BorderLayout.NORTH);
        this.add(botonera, BorderLayout.SOUTH);

        // añadir eventos a todos los botones
        for (JButton boton : botonera.getgrupoBotones()) {
            boton.addActionListener(this);
        }
    }

    // Método sobreescrito al implementar el ActionListener
    @Override
    public void actionPerformed(ActionEvent ae) {

        // Recogo en un botón qué botón concretamente he pulsado en la calculadora
        JButton boton = (JButton) ae.getSource();
        // Guardo en una variable el contenido del botón que he pulsado
        String texto = boton.getText();

        // Si el contenido del botón coincide con un número dentro del rango de 1 al 9, lo muestro en el área de texto
        if (texto.matches("[0-9]")) {
            areaTexto.append(texto);
        }

        else if (texto.matches("[+\\-*/]")) {
            operando1 = Double.parseDouble(areaTexto.getText());
            operador = texto;
            areaTexto.append(" " + operador + " ");
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

            areaTexto.append(resultado + " ");
        }

        else if (texto.equals("C")) {
            areaTexto.setText("");
            operando1 = 0;
            operador = "";
        }
    }
}
