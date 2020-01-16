package com.eaglx;

import com.eaglx.devices.Mouse;
import com.eaglx.server.Package;
import com.eaglx.server.Server;

public class Main {

    public static void main(String[] args) {
        System.out.println("jOCCenter ver0.1");
        System.out.println("################");

        Mouse mouse = new Mouse();
        if(mouse.CheckIfOk() == false){
            System.out.println("ERROR: Cannot setup the mouse's controller!");
            System.exit(-1);
        }
        else{
            System.out.println("INFO: The mouse's controller is working");
        }

        Server server = new Server();
        if(server.checkIfOk()== false) {
            System.out.println("ERROR: Cannot start TCP server!");
            System.exit(-2);
        }
        else{
            System.out.printf("INFO: TCP server is working on port %d%n", server.getServerPort());
        }
        System.out.println("INFO: Server wait for connections ...");
        if(server.start() == false){
            System.out.println("ERROR: Cannot accept connection!");
        }
        else{
            Package p = server.read();
            if(p == null){
                System.out.println("ERROR: Recive no data!");
            }
            else {
                System.out.println("INFO: Mouse new cordinates:");
                System.out.printf("X = %d%n", p.getMouseXPos());
                System.out.printf("Y = %d%n", p.getMouseYPos());
            }
        }

        System.exit(0);
    }
}
