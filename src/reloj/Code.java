/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reloj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Calendar;
import javax.swing.JPanel;

/**
 *
 * @author alexi
 */
public class Code extends JPanel {

    private static final long serialVersionUID = 1L;
    private int centroX, centroY;

    private int marcasHorarias;
    private int margenMarcasHorarias;
    private int agujaHora;
    private int agujaMinuto;
    private int agujaSegundo;

    private double angHora;
    private double angMinuto;
    private double angSegundo;
    private double angHoraPorMin;

    /**
     * Constructor. Realiza los cálculos necesarios para calcular los
     * movimientos de las agujas del reloj.
     */
    public Code() {
        Principal.diametro = 200;            // Diametro del reloj
        marcasHorarias = 10;       // Longitud de las marcas horarias
        margenMarcasHorarias = 5;  // Margen entre la circunferencia y las marcas
        // horarias
        agujaHora = 50;            // Longitud de la aguja de las horas
        agujaMinuto = 75;          // Longitud de la aguja de los minutos
        agujaSegundo = 75;         // Longitud de la aguja de los segundos
        angHora = Math.PI / 6;       // Radian del angulo de cada marca de hora
        angMinuto = Math.PI / 30;    // Radian del angulo de cada marca de minuto
        angSegundo = Math.PI / 30;   // Radian del angulo de cada marca de segundo
        angHoraPorMin = Math.PI / 360; // Radian del angulo que se mueve la aguja
        // de la hora en cada minuto

    }

    /**
     * Dibuja el reloj pero sin las agujas
     *
     * @param g2 Graphics2D Panel donde se dibuja el reloj.
     */
    private void pintarReloj(Graphics2D g2) {
        
        // Circulo
        g2.fillArc(centroX - (Principal.diametro / 2), centroY - (Principal.diametro / 2),
                Principal.diametro, Principal.diametro, 0, 360);
        
        
        g2.drawArc(centroX - (Principal.diametro / 2) - 10, centroY - (Principal.diametro / 2) - 10,
                Principal.diametro + 20, Principal.diametro + 20, 0, 360);
        g2.setColor(Color.lightGray);
        
        // Marcas horarias
        g2.translate(centroX, centroY);
        for (int i = 0; i < 12; i++) {
            g2.drawLine(0, -(Principal.diametro / 2) + margenMarcasHorarias,
                    0, -(Principal.diametro / 2) + marcasHorarias);
            g2.rotate(angHora);
        }
        g2.translate(-centroX, -centroY);

    }

    /**
     * Dibuja la aguja de la hora.
     *
     * @param g2 Graphics2D Panel donde se dibuja el reloj.
     */
    private void pintarAgujaHora(Graphics2D g2) {

        double rot = ((angHora * (Principal.hora % 12)) + (angHoraPorMin * Principal.minutos));
        g2.translate(centroX, centroY);
        g2.rotate(rot);
        g2.drawLine(0, 0, 0, -agujaHora);
        g2.rotate(-rot);
        g2.translate(-centroX, -centroY);
    }

    /**
     * Dibuja la aguja de los minutos
     *
     * @param g2 Graphics2D Panel donde se dibuja el reloj.
     */
    private void pintarAgujaMinuto(Graphics2D g2) {

        double rot = angMinuto * Principal.minutos;
        g2.translate(centroX, centroY);
        g2.rotate(rot);
        g2.drawLine(0, 0, 0, -agujaMinuto);
        g2.rotate(-rot);
        g2.translate(-centroX, -centroY);
    }

    /**
     * Dibuja la aguja de los segundos
     *
     * @param g2 Graphics2D Panel donde se dibuja el reloj.
     */
    private void pintarAgujaSegundo(Graphics2D g2) {

        double rot = angSegundo * Principal.segundos;
        g2.translate(centroX, centroY);
        g2.rotate(rot);
        g2.drawLine(0, 0, 0, -agujaSegundo);
        g2.rotate(-rot);
        g2.translate(-centroX, -centroY);

    }

    /**
     * Pinta el reloj completo, con las agujas indicando la hora correcta
     *
     * @param g Graphics Panel donde se dibuja el reloj.
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int ancho = this.getWidth();
        int alto = this.getHeight();
        centroX = (int) ancho / 2;
        centroY = (int) alto / 2;

        //g2.setBackground(Color.white);
        pintarReloj(g2);

        // Obtiene el tiempo actual
        /*Calendar Calendario=Calendar.getInstance();
        Principal.hora=Calendario.get(Calendar.HOUR);
        Principal.minutos=Calendario.get(Calendar.MINUTE);
        Principal.segundos=Calendario.get(Calendar.SECOND);*/
        
        g2.setColor(Color.cyan);
        pintarAgujaHora(g2);
        g2.setColor(Color.cyan);
        pintarAgujaMinuto(g2);
        g2.setColor(Color.lightGray);
        pintarAgujaSegundo(g2);
        repaint();
        Principal.lbl_DigitalHour.setText(Principal.hora + ":" + Principal.minutos + ":" + Principal.segundos);
    }
}
