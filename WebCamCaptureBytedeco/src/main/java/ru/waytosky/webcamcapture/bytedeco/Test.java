/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.webcamcapture.bytedeco;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;
import static org.bytedeco.javacpp.opencv_imgproc.*;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.VideoInputFrameGrabber;


/**
 * Created by gtiwari on 1/3/2017.
 */

public class Test implements Runnable {
    final int INTERVAL = 100;///you may use interval
    CanvasFrame canvas = new CanvasFrame("Web Cam");

    public Test() {
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    public void run() {

        FrameGrabber grabber = new VideoInputFrameGrabber(0); // 1 for next camera
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        IplImage img;
        IplImage grayImage;    
//        int i = 0;
        try {
            grabber.start();
            while (true) {
//                grabber.setVideoCodec(avcodec.AV_CODEC_i);
                Frame frame = grabber.grab();
//                grabber.getFormat();

                img = converter.convert(frame);
                grayImage= IplImage.create(img.width(),img.height(), IPL_DEPTH_8U, 1);
                //the grabbed frame will be flipped, re-flip to make it right
                cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise
                cvCvtColor(img, grayImage, CV_BGR2GRAY);
                //save
//                cvSaveImage((i++) + "-aa.jpg", grayImage);

                canvas.showImage(converter.convert(grayImage));

                Thread.sleep(INTERVAL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Test gs = new Test();
        Thread th = new Thread(gs);
        th.start();
    }
}