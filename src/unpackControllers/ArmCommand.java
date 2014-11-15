/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unpackControllers;

/**
 *
 * @author junxin
 */
public class ArmCommand {
    
    public void sendCommand(String[] c){
        String target=c[1];
        String movement=c[2];
        String rotation=c[3];
        String crow=c[4];
        
        switch(target){
            case "shouder":
            case "wrist":
                setArmMovement(target, movement,rotation);
                break;
            case "elbow":
                setArmMovement(target, movement);
                break;
        }
        
        setCrow(crow);
    }
    
    public void setArmMovement(String target, String movement){
        //setArmMovement
        //elbow        
    }
    
    public void setArmMovement(String target, String movement, String rotation){
        //setArmMovement
        //shoulder and wrist
    }
    
    public void setCrow(String crow){
        //setCrow
    }
}
