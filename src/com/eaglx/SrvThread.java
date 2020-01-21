package com.eaglx;

import com.eaglx.devices.Mouse;
import com.eaglx.server.Server;
import com.example.jremotecontrol.Network.Package;

import java.io.IOException;

public class SrvThread extends Thread {

    private Server server;
    private Mouse mouse;
    private boolean isToCloseServer;

    public SrvThread(Mouse mouse){
        server = new Server();
        this.mouse = mouse;
        isToCloseServer = false;
    }

    public SrvThread(Mouse mouse, int srvPort){
        server = new Server(srvPort);
        this.mouse = mouse;
        isToCloseServer = false;
    }

    public void interrupt(){
        isToCloseServer = true;
        try{
            server.stop();
        } finally {
            super.interrupt();
        }
    }

    public void run(){
        if(!server.checkIfOk()) {
            System.out.println("ERROR: Cannot start TCP server!");
            System.exit(-2);
        }
        else {
            System.out.printf("INFO: TCP server is working on port %d%n", server.getServerPort());
            while (!isToCloseServer) {
                try {
                    System.out.println("INFO: Server wait for connections ...");
                    if (!server.start()) {
                        System.out.println("ERROR: Cannot accept connection!");
                    } else {
                        System.out.println("INFO: Server connected with client");
                        Package p = null;
                        while (true) {
                            p = server.read();
                            if (p == null) {
                                System.out.println("ERROR: Recive no data!");
                                break;
                            } else {
                                if (p.getMod() == Package.Mod.MOVECURSOR) {
                                    System.out.println("INFO: Move cursor");
                                    mouse.Move(p.getMouseXPos(), p.getMouseYPos());
                                } else if (p.getMod() == Package.Mod.CLICKMOUSE) {
                                    if (p.getMouseBtnClick() == Package.MouseBtn.LEFT) {
                                        System.out.println("INFO: Left click");
                                        mouse.LClickPress();
                                        mouse.LClickRelease();
                                    } else {
                                        System.out.println("INFO: Right click");
                                        mouse.RClickPress();
                                        mouse.RClickRelease();
                                    }
                                } else {
                                    System.out.println("ERROR: Wrong mode!");
                                }
                            }
                        }
                    }
                } catch (Exception se) {
                    break;
                }
            }
        }
    }
}
