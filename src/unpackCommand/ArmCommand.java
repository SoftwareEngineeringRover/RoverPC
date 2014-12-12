/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unpackCommand;

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
            case "shoulder":
                setArmMovement(target, movement,rotation);
                break;
            case "wrist":
                setArmMovement(target, movement,rotation);
                break;
            case "elbow":
                setArmMovement(target, movement);
                break;
                //add default for error check
        }
        
        setCrow(crow);
    }
    
    public void setArmMovement(String target, String movement){
        //setArmMovement
        //elbow     
        System.out.print("Rover " +target+" move "+movement);
    }
    
    public void setArmMovement(String target, String movement, String rotation){
        //setArmMovement
        //shoulder and wrist
        System.out.print("Rover " +target+" move "+movement+" rotate "+ rotation);
    }
    
    public void setCrow(String crow){
        //setCrow
        System.out.println(crow.equals("true")?"Close":"Open");
    }
}
