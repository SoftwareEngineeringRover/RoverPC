/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camera;

import java.util.Vector;
import javax.media.CaptureDeviceInfo;
import javax.media.Format;
import javax.media.cdm.CaptureDeviceManager;
import javax.media.format.AudioFormat;
import javax.media.format.VideoFormat;
import javax.media.format.YUVFormat;

public class Camera {
        static VideoFormat videoFormat;
    static AudioFormat audioFormat;
    static CaptureDeviceInfo videoDevice;
    static CaptureDeviceInfo audioDevice;

    public static void main(String[] args) throws Exception {
        
        Vector deviceList = null;
        deviceList = CaptureDeviceManager.getDeviceList();
        CaptureDeviceInfo device = (CaptureDeviceInfo) deviceList.lastElement();
        Format[] formats = device.getFormats();
        for (int x = 0; x < formats.length; x++) {
            if (formats[x] != null && formats[x] instanceof VideoFormat) {
                videoFormat = (VideoFormat) formats[x]; //take the video format
                videoDevice = device;
            }
            if (formats[x] != null && formats[x] instanceof AudioFormat) {
                audioFormat = (AudioFormat) formats[x]; //take the audio format
                //audioDevice = device;
            }
        }
        System.out.println(audioFormat == null);
    }

}
