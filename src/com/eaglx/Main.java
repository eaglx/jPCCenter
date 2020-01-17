package com.eaglx;

import com.eaglx.devices.Mouse;
import com.example.jremotecontrol.Network.Package;
import com.eaglx.server.Server;

public class Main {

    public static void main(String[] args) {
        System.out.println("jOCCenter ver0.1");
        System.out.println("################");

        Mouse mouse = new Mouse();
        if(!mouse.CheckIfOk()){
            System.out.println("ERROR: Cannot setup the mouse's controller!");
            System.exit(-1);
        }
        else{
            System.out.println("INFO: The mouse's controller is working");
        }

        Server server = new Server();
        if(!server.checkIfOk()) {
            System.out.println("ERROR: Cannot start TCP server!");
            System.exit(-2);
        }
        else{
            System.out.printf("INFO: TCP server is working on port %d%n", server.getServerPort());
        }
        System.out.println("INFO: Server wait for connections ...");
        if(!server.start()){
            System.out.println("ERROR: Cannot accept connection!");
        }
        else{
            System.out.println("INFO: Server connected with client");
            Package p = null;
            while(true) {
                p = server.read();
                if (p == null) {
                    System.out.println("ERROR: Recive no data!");
                    break;
                } else {
                    if(p.getMod() == Package.Mod.MOVECURSOR) {
                        mouse.Move(p.getMouseXPos(), p.getMouseYPos());
                    }
                    else if(p.getMod() == Package.Mod.CLICKMOUSE) {
                        if(p.getMouseBtnClick() == Package.MouseBtn.LEFT){
                            mouse.LClickPress();
                            mouse.LClickRelease();
                        }
                        else {
                            mouse.RClickPress();
                            mouse.RClickRelease();
                        }
                    }
                    else {
                        System.out.println("ERROR: Wrong mode!");
                    }
                }
            }
        }

        server.stop();

        System.exit(0);
    }
}
