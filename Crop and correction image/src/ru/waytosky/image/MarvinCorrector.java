/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.image;

import java.util.ArrayList;
import java.util.List;
import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;
import marvin.plugin.MarvinImagePlugin;
import marvin.util.MarvinPluginLoader;

/**
 *
 * @author ayrat
 */
public class MarvinCorrector {

    public static void mergePhotosApp() {

//         1. load images 01.jpg, 02.jpg, ..., 05.jpg into a List
//        List<MarvinImage> images = new ArrayList<MarvinImage>(); 
//        for(int i=0; i<=1; i++){ 
//            images.add(MarvinImageIO.loadImage("./res/0"+i+".jpg")); 
//        } 
        MarvinImage img = MarvinImageIO.loadImage("./res/00.jpg");

        // 2. Load plug-in and process the image 
//        MarvinImagePlugin merge = MarvinPluginLoader.loadImagePlugin("org.marvinproject.image.combine.mergePhotos"); 
        MarvinImagePlugin merge = MarvinPluginLoader.loadImagePlugin("org.marvinproject.image.color.invert.jar");
//        merge.setAttribute("threshold", 38); 

        // 3. Process the image list and save the output 
//        MarvinImage output = images.get(1).clone(); 
        merge.process(img, img);
        MarvinImageIO.saveImage(img, "./res/result.jpg");
    }

    public static void main(String[] args) {
        mergePhotosApp();
    }
}
