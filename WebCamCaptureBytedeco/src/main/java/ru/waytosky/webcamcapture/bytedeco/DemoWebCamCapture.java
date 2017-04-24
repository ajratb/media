/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.webcamcapture.bytedeco;

/**
 *
 * @author ayrat
 */
import static org.bytedeco.javacpp.opencv_core.cvFlip;
//import static org.bytedeco.javacpp.opencv_highgui.cvSaveImage;
 
//the listing in the opencv class file for the following class is
//org/bytedeco/javacpp/opencv_core$IplImage.class
import org.bytedeco.javacpp.opencv_core.IplImage;
 
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.VideoInputFrameGrabber;
 
 
public class DemoWebCamCapture {
    static final int INTERVAL=5000;
    IplImage image;
    static  CanvasFrame canvas = new CanvasFrame("Web Cam");
 
//    public static void main(String[] args) {
//        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
//        FrameGrabber grabber = new VideoInputFrameGrabber(0); 
//        int i=0;
//        try {
//            grabber.start();
//            IplImage img;
//            while (true) {
//                img = grabber.grab();
//                if (img != null) {
//                    // show image on window
//                    canvas.showImage(img);
//                }
//                Thread.sleep(INTERVAL);
//            }
//        } catch (Exception e) {
//        e.printStackTrace();
//        }
//    }
}
