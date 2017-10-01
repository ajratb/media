/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.mrvn;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javax.swing.SwingUtilities;

import marvin.gui.MarvinImagePanel;
import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;
import marvin.video.MarvinJavaCVAdapter;
import marvin.video.MarvinVideoInterface;
import marvin.video.MarvinVideoInterfaceException;

/**
 *
 * @author Ayrat
 */
public class МainController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    private MarvinImagePanel imagePanel;
    private MarvinImage image;

    private MarvinVideoInterface videoAdapter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//
//        videoAdapter = new MarvinJavaCVAdapter();
//        try {
//            videoAdapter.connect(0);
//        } catch (MarvinVideoInterfaceException ex) {
//            Logger.getLogger(МainController.class.getName()).log(Level.SEVERE, null, ex);
//        }

        final SwingNode swingNode = new SwingNode();
        createAndSetSwingContent(swingNode);
//       
        anchorPane.getChildren().add(swingNode);
        anchorPane.setScaleX(0.8);
        anchorPane.setScaleY(0.8);

    }

    private void createAndSetSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                imagePanel = new MarvinImagePanel();
                image = MarvinImageIO.loadImage("./res/tucano.jpg");
                imagePanel.setImage(image);

                swingNode.setContent(imagePanel);
                
//                while(true){
//                    try {
//                        // Request a video frame and set into the VideoPanel
//                        image = videoAdapter.getFrame();
//                        imagePanel.setImage(image);
//                    } catch (MarvinVideoInterfaceException ex) {
//                        Logger.getLogger(МainController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                        
//                }

                
            }
        });
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");

    }

}
