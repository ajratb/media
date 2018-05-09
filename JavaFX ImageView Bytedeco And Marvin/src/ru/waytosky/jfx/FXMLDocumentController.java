/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.jfx;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import org.bytedeco.javacv.FrameGrabber;

/**
 *
 * @author Ayrat
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ImageView imgView;
    private BytedecoWCamService service;
    int i;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (!service.isRunning()) {
            System.out.println("service is restarting");
            service.restart();
        } else {

            System.out.println("service is cancelling");
            service.cancel();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new BytedecoWCamService(640, 480,i);
        imgView.imageProperty().bind(service.valueProperty());
    }

    @FXML
    void onAction_btnSTOP(ActionEvent event) {
        if(i==0)i=-1;else i=0;
        service.reset();
        service=null;
        service = new BytedecoWCamService(640, 480, i);
        imgView.imageProperty().bind(service.valueProperty());
            
    }

}
