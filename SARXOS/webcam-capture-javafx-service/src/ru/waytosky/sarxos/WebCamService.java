package ru.waytosky.sarxos;

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
	
//	private final WebcamResolution resolution ;
        private final Dimension dims;
        private static final JHGrayFilter GRAY = new JHGrayFilter();
        
	public WebCamService(Webcam cam, Dimension dimens) {
		this.cam = cam ;
		this.dims = dimens;
		cam.setCustomViewSizes(new Dimension[] {dims});
		cam.setViewSize(dims);
	}
	
	public WebCamService(Webcam cam) {
//		this(cam, WebcamResolution.SVGA);
                this(cam, new Dimension(640, 480));
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
		return dims.width ;
	}
	
	public int getCamHeight() {
		return dims.height ;
	}
		
}

