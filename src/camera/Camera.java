/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camera;

/**
 *
 * @author junxin
 */
public class Camera {
    int[] sendCamList=new int[2];
    
    public void setSendCamList(String[] list){
        sendCamList[0]=Integer.parseInt(list[1]);
        sendCamList[1]=Integer.parseInt(list[2]);
    }
}
