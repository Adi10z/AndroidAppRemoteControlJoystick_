package com.example.androidappremotecontroljoystick.Model;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class FGPlayer {
    private String aileronSetCommand = "set /controls/flight/aileron ";
    private String elavatorSetCommand = "set /controls/flight/elevator ";
    private String rudderSetCommand = "set /controls/flight/rudder ";
    private String throttleSetCommand = "set /controls/engines/current-engine/throttle ";
    private PrintWriter out;
    private double aileron;
    private double elevator;
    private double rudder;
    private double throttle;
    private BlockingDeque<Runnable> dispatchQueue = new LinkedBlockingDeque<>();

    //singleton design pattern
    /*
    public FGPlayer(String ipAddr, int port) {
        this.ipAddr = ipAddr;
        this.port = port;
        this.aileron = 0;
        this.elevator = 0;
        this.rudder = 0;
        this.throttle = 0;
    }

     */


    public void setAileron(double aileron) {
        if (this.out != null) {
            try {
                dispatchQueue.put(() -> {
                    this.aileron = aileron;
                    connectServer(aileronSetCommand, this.aileron);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void setElevator(double elevator) {
        if (this.out != null) {
            try {
                dispatchQueue.put(() -> {
                    this.elevator = elevator;
                    connectServer(elavatorSetCommand, this.elevator);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRudder(double rudder) {
        if (this.out != null) {
            try {
                dispatchQueue.put(() -> {
                    this.rudder = rudder;
                    connectServer(rudderSetCommand, this.rudder);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setThrottle(double throttle) {
        if (this.out != null) {
            try {
                dispatchQueue.put(() -> {
                    this.throttle = throttle;
                    connectServer(throttleSetCommand, this.throttle);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    public String getipAddr() {
        return this.ipAddr;
    }

    public int getPort() {
        return port;
    }

     */



    /*
    // connect server
    public void Connect() {
        //active object design pattern
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    InetAddress Ip = InetAddress.getByName(ip);
                    // create new socket
                    fg = new Socket(Ip, port);
                    System.out.println("connected");
                    out = new PrintWriter(fg.getOutputStream());
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

     */


    public void connect(String ip, int port) {
        try {
            Socket fg = new Socket(ip, port);
            this.out = new PrintWriter(fg.getOutputStream(), true);
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        new Thread(() -> {
            while (true) {
                try {
                    dispatchQueue.take().run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void connectServer(String command, double val){
        String connectCommand = command + val + "\r\n";
        out.print(connectCommand);
        out.flush();
    }


/*
    public void connectServer(String command, float val) {
        final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(1);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    fg = new Socket(getipAddr(), getPort());
                    System.out.println("connected");
                    out = new PrintWriter(fg.getOutputStream(), true);

                    final String connectCommand = command + val + "\r\n";
                    out.print(connectCommand);
                    out.flush();
                    System.out.println(connectCommand);

                    closeSocket();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

 */

/*
    // close the connection with the server
    public void closeSocket() {
        if (out != null) {
            out.close();
        }
        try {
            if(fg != null) {
                fg.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 */
}

