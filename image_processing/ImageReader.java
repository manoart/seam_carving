package image_processing;

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageReader {

  private BufferedImage bufferedImage;

  public ImageReader() {
    this(null);
  }

  public ImageReader(String imageSource) {
    try {
      this.setBufferedImage(imageSource);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  private void setBufferedImage(String imageSource) throws IOException {
    File image = new File(imageSource);
    this.bufferedImage = ImageIO.read(image);
  }

  public BufferedImage getBufferedImage() {
    return this.bufferedImage;
  }

}
