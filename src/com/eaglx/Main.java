package com.eaglx;

import com.eaglx.devices.Mouse;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner usrInput = new Scanner(System.in);

        System.out.println("jOCCenter ver0.2");
        System.out.println("################");

        Mouse mouse = new Mouse();
        if(!mouse.CheckIfOk()){
            System.out.println("ERROR: Cannot setup the mouse's controller!");
            System.exit(-1);
        }
        else{
            System.out.println("INFO: The mouse's controller is working");
        }

        System.out.println("Start server? (Y/N)");
        String usrDecision = usrInput.nextLine();
        if(usrDecision.equals("Y") || usrDecision.equals("y")) {
            SrvThread srv = new SrvThread(mouse);
            srv.start();
            System.out.println("MAIN-TH-INFO: To close the server press q");
            System.out.println("Start server? (Y/N)");
            while(true) {
                usrDecision = usrInput.nextLine();
                if (usrDecision.equals("q")) {
                    System.out.println("MAIN-TH-INFO: Closing the server");
                    srv.interrupt();
                    try {
                        srv.join();
                        break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        break;
                    }
                }
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("INFO: The server is closed");
        System.exit(0);
    }
}
