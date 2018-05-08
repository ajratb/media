/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.mrvn;

import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;
import marvin.video.MarvinJavaCVAdapter;
import marvin.video.MarvinVideoInterface;
import marvin.video.MarvinVideoInterfaceException;

/**
 *
 * @author Ayrat
 */
public class WebcamPicture {
    public static void main(String[] args) {
		try{
			MarvinVideoInterface videoAdapter = new MarvinJavaCVAdapter();
			videoAdapter.connect(0);
			MarvinImage image = videoAdapter.getFrame();
			MarvinImageIO.saveImage(image, "./res/webcam_picture.jpg");
		} catch(MarvinVideoInterfaceException e){
			e.printStackTrace();
		}
	}
}
