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
public class ControllerCommand {

    MotorCommand mc;
    ArmCommand ac;

    public ControllerCommand() {
        mc = new MotorCommand();
        ac = new ArmCommand();
    }

    public void sendCommand(String c) {
        String[] commands = c.split(" ");
        switch (commands[0]) {
            case "0":
                mc.sendCommand(commands);
                break;
            case "1":
                ac.sendCommand(commands);
                break;
        }
    }
}
