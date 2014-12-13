package unpackCommand;

/**
 * Receives and does Arm commands accordingly
 * @author Jun
 */
public class ArmCommand {
    
    /**
     * Moves shoulder, wrist, or elbow
     * @param c 
     */
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
    
    /**
     * Sets arm movement as decided from UserServer joystick
     * @param target
     * @param movement 
     */
    public void setArmMovement(String target, String movement){
        //setArmMovement
        //elbow     
        System.out.print("Rover " +target+" move "+movement);
    }
    
    /**
     * Sets arm movement as decided from UserServer joystick
     * @param target
     * @param movement
     * @param rotation 
     */
    public void setArmMovement(String target, String movement, String rotation){
        //setArmMovement
        //shoulder and wrist
        System.out.print("Rover " +target+" move "+movement+" rotate "+ rotation);
    }
    
    /**
     * Sets arm movement as decided from UserServer joystick
     * @param crow 
     */
    public void setCrow(String crow){
        //setCrow
        System.out.println(crow.equals("true")?"Close":"Open");
    }
}
