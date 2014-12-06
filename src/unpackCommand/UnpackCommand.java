/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unpackCommand;

import camera.ImageBuffer;

/**
 *
 * @author junxin
 */
public class UnpackCommand {

    MotorCommand mc;
    ArmCommand ac;
    ImageBuffer camera;

    public UnpackCommand(ImageBuffer c) {
        mc = new MotorCommand();
        ac = new ArmCommand();
        camera=c;
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
            case "2":
                camera.setSendCamList(commands);
                break;
        }
    }
}
