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

    // Crear la ventana principal (constructor)
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

        if (texto.matches("[0-9]")) {
            // Añadir dígito al área de texto para que quede visible
            areaTexto.append(texto);

        } else if (texto.matches("[+\\-*/]")) {
            String valor = areaTexto.getText().trim();
            if (!valor.isEmpty()) {
                try {
                    operando1 = Double.parseDouble(valor);
                    operador = texto;
                    areaTexto.append(" " + operador + " ");
                } catch (NumberFormatException e) {
                    // Si no es número, no hacer nada
                }
            }

        } else if (texto.equals("=")) {
            String pantalla = areaTexto.getText().trim();
            String[] partes = pantalla.split(" ");
            if (partes.length >= 3 && !operador.isEmpty()) {
                try {
                    double operando2 = Double.parseDouble(partes[2]);
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
                    operador = "";

                } catch (NumberFormatException e) {
                    // no hacer nada
                }
            }

        } else if (texto.equals("C")) {
            areaTexto.setText("");
            operando1 = 0;
            operador = "";
        }
    }
}
