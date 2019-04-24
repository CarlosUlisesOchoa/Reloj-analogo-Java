/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reloj;

import java.util.Calendar;

/**
 *
 * @author alexi
 */
public class RefreshTime implements Runnable {

    @Override
    public synchronized void run() {

        try {
            while (true) {
                
                Principal.Calendario.add(Calendar.SECOND, 1);
                
                Principal.hora=Principal.Calendario.get(Calendar.HOUR);
                Principal.minutos=Principal.Calendario.get(Calendar.MINUTE);
                Principal.segundos=Principal.Calendario.get(Calendar.SECOND);
                
                if(Principal.Calendario.get(Calendar.AM_PM) == Calendar.PM) {
                    Principal.lbl_DigitalAMPM.setText("PM");
                } else { 
                   Principal.lbl_DigitalAMPM.setText("AM");
                }
                
                if(Principal.hora == Principal.hora_Alarma && Principal.minutos == Principal.minuto_Alarma) {
                    if(Principal.alarm == true && Principal.alarm_RingStatus == false) {
                        Principal.StartAlarm();
                    }
                }
                
                Thread.sleep(1000);
            }

        } catch (Exception e) {

        }

    }

}
