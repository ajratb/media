/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.webcamcapture.bytedeco;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;
import static org.bytedeco.javacpp.opencv_imgproc.*;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;


/**
 * Created by gtiwari on 1/3/2017.
 */

public class Test implements Runnable {
    final int INTERVAL = 100;///you may use interval
    CanvasFrame canvas = new CanvasFrame("Web Cam");
private Java2DFrameConverter converter2D = new Java2DFrameConverter();
    public Test() {
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    public void run() {

        FrameGrabber grabber = new OpenCVFrameGrabber(0);//VideoInputFrameGrabber(0); // 1 for next camera
        
        grabber.setImageWidth(3312);
        grabber.setImageHeight(4416);
//        grabber.setImageHeight(1836);
//        grabber.setImageWidth(2754);
//        grabber.setFormat("YUY2");
//
//         grabber.setImageWidth(640);
//        grabber.setImageHeight(480);

        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        IplImage img;
        IplImage grayImage;    
        int i = 0;
        try {
            grabber.start();
            while (true) {
//                grabber.setVideoCodec(avcodec.AV_CODEC_i);
                Frame frame = grabber.grab();
//                grabber.getFormat();

                img = converter.convert(frame);
                grayImage= IplImage.create(img.width(),img.height(), IPL_DEPTH_8U, 1);
                //the grabbed frame will be flipped, re-flip to make it right
//                cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise
                cvCvtColor(img, grayImage, CV_BGR2GRAY);
                //save
                cvSaveImage((i++) + "-aa.jpg", grayImage);
                
                if(i==7){
                    BufferedImage bimg=  converter2D.getBufferedImage(frame, 1.0);
                    File outputFile = new File("bimg.png");
//
                            try {
                                ImageIO.write(bimg, "png", outputFile);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                }

                canvas.showImage(converter.convert(grayImage));

//                Thread.sleep(INTERVAL);
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
