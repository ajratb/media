package main.java;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.util.jh.*;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class WebCamService extends Service<Image> {

	private final Webcam cam ;
	
	private final WebcamResolution resolution ;
        private static final JHGrayFilter GRAY = new JHGrayFilter();
        
	public WebCamService(Webcam cam, WebcamResolution resolution) {
		this.cam = cam ;
		this.resolution = resolution;
		cam.setCustomViewSizes(new Dimension[] {resolution.getSize()});
		cam.setViewSize(resolution.getSize());
	}
	
	public WebCamService(Webcam cam) {
		this(cam, WebcamResolution.SVGA);
	}
	
	@Override
	public Task<Image> createTask() {
		return new Task<Image>() {
			@Override
			protected Image call() throws Exception {
				
				try {
					cam.open();
					while (!isCancelled()) {
						if (cam.isImageNew()) {
							BufferedImage bimg = cam.getImage();
                                                        GRAY.filter(bimg, bimg);
							updateValue(SwingFXUtils.toFXImage(bimg, null));
						}
					}
					System.out.println("Cancelled, closing cam");
					cam.close();
					System.out.println("Cam closed");
					return getValue();
				} finally {
					cam.close();
				}
			}
			
		};
	}
	

	public int getCamWidth() {
		return resolution.getSize().width ;
	}
	
	public int getCamHeight() {
		return resolution.getSize().height ;
	}
		
}

