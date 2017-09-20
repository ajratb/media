/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.sarxos;

import java.awt.image.BufferedImage;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.IplImage;
import static org.bytedeco.javacpp.opencv_core.IPL_DEPTH_8U;
import static org.bytedeco.javacpp.opencv_core.cvFlip;
import static org.bytedeco.javacpp.opencv_imgproc.CV_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.cvCvtColor;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.VideoInputFrameGrabber;

/**
 *
 * @author Ayrat
 */
public class BytedecoWCamService extends Service<Image> {

    private final int INTERVAL = 1000;///you may use interval
    private final FrameGrabber grabber = new VideoInputFrameGrabber(0); // 1 for next camera
    private final OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
    private Java2DFrameConverter converter2D = new Java2DFrameConverter();
    public BytedecoWCamService(int width, int height) {
        //
        grabber.setImageWidth(width);
        grabber.setImageHeight(height);
    }

    @Override
    protected Task<Image> createTask() {
        return new Task<Image>() {
            @Override
            protected Image call() throws Exception {
                IplImage img;
                IplImage grayImage;
                try {
                    grabber.start();
                    while (!isCancelled()) {
                        Frame frame = grabber.grab();

                        img = converter.convert(frame);
                        grayImage = opencv_core.IplImage.create(img.width(), img.height(), IPL_DEPTH_8U, 1);
//                        

                        //the grabbed frame will be flipped, re-flip to make it right
//                        cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise
                        cvCvtColor(img, grayImage, CV_BGR2GRAY);
                        
                        BufferedImage bimg = converter2D.convert(converter.convert(grayImage));
                        updateValue(SwingFXUtils.toFXImage(bimg, null));
                         Thread.sleep(INTERVAL);
                    }
                    System.out.println("Cancelled, closing cam");
                    grabber.close();
                    System.out.println("Cam closed");
                    return getValue();
                } finally {
                    grabber.close();
                }
            }

        };

    }
    
    public int getCamWidth() {
		return grabber.getImageWidth();
	}
	
	public int getCamHeight() {
		return grabber.getImageHeight() ;
	}

}
