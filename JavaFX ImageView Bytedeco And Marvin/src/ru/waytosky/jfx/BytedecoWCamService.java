/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.jfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.IplImage;
import static org.bytedeco.javacpp.opencv_core.IPL_DEPTH_8U;
import static org.bytedeco.javacpp.opencv_imgproc.CV_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.cvCvtColor;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.VideoInputFrameGrabber;
import javax.imageio.ImageIO;

/**
 *
 * @author Ayrat
 */
public class BytedecoWCamService extends Service<Image> {

    private final int INTERVAL = 100;///you may use interval
    private final  FrameGrabber grabber ; // 1 for next camera
    private final OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
    private Java2DFrameConverter converter2D = new Java2DFrameConverter();

    public BytedecoWCamService(int width, int height, int device) {
        grabber= new VideoInputFrameGrabber(device);
        grabber.setImageWidth(width);
        grabber.setImageHeight(height);
    }

//    public void forceStop() throws FrameGrabber.Exception{
//        grabber.stop();
//        grabber.close();
//    }
    @Override
    protected Task<Image> createTask() {
        return new Task<Image>() {
            @Override
            protected Image call() throws Exception {
                IplImage img;
                IplImage grayImage;

                try {
                    System.out.println("before grabber start");
                    try {
                        grabber.start();
                    } catch (FrameGrabber.Exception e) {
                        grabber.restart();
//                    grabber.start();
                    }

                    System.out.println("grabber started");
                    while (!isCancelled()) {
                        Frame frame = grabber.grab();
                        img = converter.convert(frame);
                        grayImage = opencv_core.IplImage.create(img.width(), img.height(), IPL_DEPTH_8U, 1);
//                        

                        //the grabbed frame will be flipped, re-flip to make it right
//                        cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise
                        cvCvtColor(img, grayImage, CV_BGR2GRAY);

                        BufferedImage bimg = converter2D.convert(converter.convert(grayImage));
//                      
//                        File outputFile = new File("bimg.png");
//
//                        try {
//                            ImageIO.write(bimg, "png", outputFile);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }

//                        BufferedImage bimg = converter2D.convert(converter.convert(img));
//System.out.println("before value update");                        
                        updateValue(SwingFXUtils.toFXImage(bimg, null));
//                        Thread.sleep(INTERVAL);
                    }
                    System.out.println("Cancelled, closing cam");
                    grabber.stop();

                    System.out.println("Cam closed");
                    return getValue();
                } finally {
//                    grabber.stop();
                    grabber.close();
                }
            }

        };

    }

    public int getCamWidth() {
        return grabber.getImageWidth();
    }

    public int getCamHeight() {
        return grabber.getImageHeight();
    }

}
