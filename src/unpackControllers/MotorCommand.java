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
public class MotorCommand {
    
    public void sendCommand(String[] c){
        int speed=Integer.parseInt(c[1]);
        int degree=Integer.parseInt(c[2]);
        setMotorSpeed(speed);
        setDegree(degree);
    }
    
    public void setMotorSpeed(int speed){
        //set robot motor speed
    }
    
    public void setDegree(int degree){
        //set robot degree
    }
}
