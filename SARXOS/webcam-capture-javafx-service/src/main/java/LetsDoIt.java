/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ayrat
 */
public class LetsDoIt {

    public static void main(String[] args) {
        Webcam webcam = Webcam.getWebcams().get(0);
//        webcam.setCustomViewSizes(new Dimension[]{
//            WebcamResolution.CIF.getSize(),
//            WebcamResolution.HVGA.getSize(), // etc
//        });
//        webcam.setViewSize(WebcamResolution.HVGA.getSize());
//        System.out.println(webcam.getName());
        webcam.open();

        try {
            ImageIO.write(webcam.getImage(), "PNG", new File("hello-world.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
