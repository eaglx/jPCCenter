package com.eaglx;

import com.eaglx.devices.Mouse;

public class Main {

    public static void main(String[] args) {
        System.out.println("jOCCenter ver0.1");
        System.out.println("################");

        Mouse mouse = new Mouse();
        if(mouse.CheckIfOk() == false){
            System.out.println("ERROR: Cannot setup the mouse's controller!");
            System.exit(-1);
        }


        System.exit(0);
    }
}
