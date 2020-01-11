package com.eaglx;

import com.eaglx.devices.Mouse;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        System.out.println("jOCCenter ver0.1");
        System.out.println("################");

        Mouse mouse = new Mouse();
        if(mouse.CheckIfOk() == false){
            System.out.println("ERROR: Cannot setup the mouse's controller!");
            System.exit(-1);
        }

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
