package com.eaglx.devices;

import java.awt.event.KeyEvent;

public class Keyboard extends Device {  // TBD
    public Keyboard(){
        super();
    }

    public void WriteKey(char chK){
        if(Character.isUpperCase(chK)) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress((int)chK);
            robot.keyRelease((int)chK);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        }
        else {
            robot.keyPress((int)chK);
            robot.keyRelease((int)chK);
        }
    }
}
