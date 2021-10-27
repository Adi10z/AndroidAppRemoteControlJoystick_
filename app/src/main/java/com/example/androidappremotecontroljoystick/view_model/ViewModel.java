package com.example.androidappremotecontroljoystick.view_model;

//import android.support.v7.app.AppCompatActivity;

import com.example.androidappremotecontroljoystick.Model.FGPlayer;

public class ViewModel {


    //private EditText ip;
    //private EditText port;
    //private String ip;
    //private int port;
    private final FGPlayer fgPlayer;
    private String newIpAddr;
    private int newPort;


    public ViewModel(FGPlayer fgPlayer) {
        this.fgPlayer = fgPlayer;
    }

    public void connect(String ip, int port) {
        newIpAddr = ip;
        newPort = port;
        try {
            this.fgPlayer.connect(newIpAddr, newPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    public void newConnectServer(String nIp, String nPort) {
        newIpAddr = nIp;
        newPort = nPort;
        if (newIpAddr != null && newPort != null) {
            fgPlayer = new FGPlayer(newIpAddr, Integer.parseInt(newPort));
        }
    }
     */

    public boolean isFGPlayerNull() {
        if (fgPlayer != null) {
            return true;
        } else {
            return false;
        }
    }

    public void newSetAileronAndElevator(double aileron, double elevator) {
        this.fgPlayer.setAileron(aileron);
        this.fgPlayer.setElevator(elevator);
    }
    /*
    public void newSetAileronAndElevator(float aileron, float elevator) {
        float newAileron = aileron;
        float newElevator = elevator;
        if (isFGPlayerNull() == true) {
            fgPlayer.setAileron(newAileron);
            fgPlayer.setElevator(newElevator);
        }
    }

     */

    public void newSetAileron(double aileron) {
        this.fgPlayer.setAileron(aileron);
        //
    }

    public void newSetElevator(double elevator) {
        this.fgPlayer.setElevator(elevator);
        //
    }

    public void newSetRudder(double rudder) {
        this.fgPlayer.setRudder(rudder);
        //float newRudder = rudder;
        //if (isFGPlayerNull() == true) {
        //    fgPlayer.setRudder(newRudder);
        //}
    }

    public void newSetThrottle(double throttle) {
        this.fgPlayer.setThrottle(throttle);
        //float newThrottle = throttle;
        //if (isFGPlayerNull() == true) {
        //    fgPlayer.setThrottle(newThrottle);
        //}
    }


}

