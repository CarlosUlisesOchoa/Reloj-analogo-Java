/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reloj;

/**
 *
 * @author Shane
 */
public class Alarm implements Runnable {

    @Override
    public synchronized void run() {

        boolean status = true;
        try {
            while (true) {
                
                Principal.lbl_Alarm.setVisible(status);
                
                if(status == true) {
                    status = false;
                } else {
                    status = true;
                }
                Thread.sleep(500);
            }

        } catch (Exception e) {

        }

    }
    
    public void selfStop() {
        Principal.lbl_Alarm.setVisible(false);
    }

}