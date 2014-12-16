package unpackCommand;

/**
 * Accepts Motor Commands of Motor Speed and degree
 * @author Jun
 */
public class MotorCommand {
    
    public void sendCommand(String[] c){
        int speed=Integer.parseInt(c[1]);
        int degree=Integer.parseInt(c[2]);
        setMotorSpeed(speed);
        setDegree(degree);
    }
    
    /**
     * Sets the robots motor speed
     * @param speed 
     */
    public void setMotorSpeed(int speed){
        System.out.print("Motor speed:"+speed );
    }
    
    /**
     * Sets the robot motors degree
     * @param degree 
     */
    public void setDegree(int degree){
        System.out.println(",Motor degree"+degree );
    }
}
