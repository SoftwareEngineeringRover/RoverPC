package unpackCommand;

import camera.ImageBuffer;

/**
 * Sends Motor and Arm commands accordingly
 * @author Jun
 */
public class UnpackCommand {

    MotorCommand mc;
    ArmCommand ac;
    ImageBuffer camera;

    /**
     * creates commands
     * @param c 
     */
    public UnpackCommand(ImageBuffer c) {
        mc = new MotorCommand();
        ac = new ArmCommand();
        camera=c;
    }

    /**
     * Sends commands to correct class
     * @param c 
     */
    public void sendCommand(String c) {
        String[] commands = c.split(" ");
        switch (commands[0]) {
            case "0":
                mc.sendCommand(commands);
                break;
            case "1":
                ac.sendCommand(commands);
                break;
            case "2":
                camera.setSendCamList(commands);
                break;
                //set default catch error
        }
    }
}
