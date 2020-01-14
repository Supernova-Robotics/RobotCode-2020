/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.math;

/**
 * This is to make the interpretation of boolean values easier. Readings from buttons or
 * light sensors can be used. Edges are automatically detected.
 */
public class CustomButton{
    private boolean currentState = false;
    private boolean lastState = false;
    private boolean changed = false;

    public void update(boolean input){
        lastState = currentState;
        currentState = input;
        if(lastState != currentState){
            changed = true;
        }else {
            changed = false;
        }
    }

    public boolean get(){
        return currentState;
    }

    public boolean isPressed(){
        return currentState && changed;
    }

    public boolean isReleased(){
        return (!currentState) && changed;
    }

    public boolean isChanged(){
        return changed;
    }

    public void reset(){
        currentState = false;
        lastState = false;
        changed = false;
    }
}

