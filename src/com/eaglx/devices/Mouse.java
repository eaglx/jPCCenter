package com.eaglx.devices;

import java.awt.*;
import java.awt.event.InputEvent;
import static java.awt.MouseInfo.*;

public class Mouse extends Device {
    private int xCurrentPosition;
    private int yCurrentPosition;

    public Mouse(){
        super();
        PointerInfo a = getPointerInfo();
        Point b = a.getLocation();
        xCurrentPosition = (int) b.getX();
        yCurrentPosition = (int) b.getY();
    }

    public void Move(int xPosition, int yPosition) {
        if((xCurrentPosition == xPosition) && (yCurrentPosition == yPosition)){
            ;
        }
        else {
            robot.mouseMove(xPosition, yPosition);
            xCurrentPosition = xPosition;
            yCurrentPosition = yPosition;
        }
    }

    public void LClickPress(){
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void LClickRelease(){
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void RClickPress(){
        robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
    }

    public void RClickRelease(){
        robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
    }
}
