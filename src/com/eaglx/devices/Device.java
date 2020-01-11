package com.eaglx.devices;

import java.awt.*;

public class Device {
    protected Robot robot;

    public Device() {
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            robot = null;
        }
    }

    public boolean CheckIfOk(){
        if(robot == null) {
            return false;
        }
        else {
            return true;
        }
    }
}
