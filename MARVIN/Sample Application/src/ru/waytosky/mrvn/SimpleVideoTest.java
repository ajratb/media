/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.mrvn;

import java.util.logging.Level;
import java.util.logging.Logger;
 import javax.swing.JFrame;
import marvin.gui.MarvinImagePanel;
import marvin.image.MarvinImage;
import marvin.video.MarvinJavaCVAdapter;
import marvin.video.MarvinVideoInterface;
import marvin.video.MarvinVideoInterfaceException;
public class SimpleVideoTest extends JFrame implements Runnable{
       
        private MarvinVideoInterface    videoAdapter;
        private MarvinImage                             image;
        private MarvinImagePanel                videoPanel;
       
        public SimpleVideoTest(){
                super("Simple Video Test");
       
                // Create the VideoAdapter and connect to the camera
                videoAdapter = new MarvinJavaCVAdapter();
            try {
                videoAdapter.connect(1);
            } catch (MarvinVideoInterfaceException ex) {
                Logger.getLogger(SimpleVideoTest.class.getName()).log(Level.SEVERE, null, ex);
            }
               
                // Create VideoPanel
                videoPanel = new MarvinImagePanel();
                add(videoPanel);
               
                // Start the thread for requesting the video frames
                new Thread(this).start();
               
                setSize(800,600);
                setVisible(true);
        }
       
        public static void main(String[] args) {
                SimpleVideoTest t = new SimpleVideoTest();
                t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        @Override
        public void run() {
                while(true){
                    try {
                        // Request a video frame and set into the VideoPanel
                        image = videoAdapter.getFrame();
                    } catch (MarvinVideoInterfaceException ex) {
                        Logger.getLogger(SimpleVideoTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        videoPanel.setImage(image);
                }
        }
}

