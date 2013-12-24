package image_processing;

import java.io.IOException;
import java.io.File;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ImageWriter {

  private BufferedImage bufferedImage;

  private int[][] imageSource;

  private File image;

  public ImageWriter() {
    this(null);
  }

  public ImageWriter(int[][] imageSource) {
    this.setImageSource(imageSource);
  }

  private void setImageSource(int[][] imageSource) {
    this.imageSource = imageSource;
    this.image = null;
    this.bufferedImage = new BufferedImage(imageSource[0].length, imageSource.length,BufferedImage.TYPE_INT_RGB);
  }

  public BufferedImage getBufferedImage() {
    return this.bufferedImage;
  }

  public void writeBrightnessImage() {
    for (int i = 0; i < this.imageSource.length; i ++) {
      for (int j = 0; j < this.imageSource[0].length; j++) {
        int monochrome = this.imageSource[i][j] / 3;
        int rgb = monochrome << 16;
        rgb += monochrome << 8;
        rgb += monochrome;
        this.bufferedImage.setRGB(j, i, rgb);
      }
    }
    this.writeImage();
  }

  private void writeImage() {
    try {
      this.image = new File("brightness.png");
      ImageIO.write(this.bufferedImage, "png", image);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  public File getImage() {
    return this.image;
  }
}
