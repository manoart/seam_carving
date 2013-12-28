package image_processing;

import java.io.IOException;
import java.io.File;

import java.awt.image.BufferedImage;

import java.util.List;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import seam_carving.Seam;

/**
 * Class to write images and save them persistantly.
 * Preferably used to write the image after the seam carving
 * and the tests going along with it.
 */
public class ImageWriter {

  /** The BufferedImage that is to be written into a file. */
  private BufferedImage bufferedImage;

  /** The source for the BufferedImage (brightness or energy). */
  private int[][] imageSource;

  /** The file that is to be written. */
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
    this.writeMonochromeImage("brightness.png", 3);
  }

  public void writeEnergyImage() {
    this.writeMonochromeImage("energy.png", 3);
  }

  public void writeSeamImage(List<Seam> seams) {
    int height = this.imageSource.length;
    int width = this.imageSource[0].length;
    for (int i = 0; i < height; i ++) {
      for (int j = 0; j < width; j++) {
        int monochrome = this.imageSource[i][j] / 3;
        int rgb = monochrome << 16;
        rgb += monochrome << 8;
        rgb += monochrome;
        this.bufferedImage.setRGB(j, i, rgb);
      }
    }
    for (Seam seam: seams) {
      int[] path = seam.getPath();
      int color = seam.getColor();
      int x = seam.getStartPoint();
      for (int y = 0; y < path.length; y++) {
        x += path[y];
        this.bufferedImage.setRGB(x, y, color);
      }
    }
    this.writeImage("seams.png");
  }

  private void writeMonochromeImage(String fileName, int divisor) {
    int height = this.imageSource.length;
    int width = this.imageSource[0].length;
    for (int i = 0; i < height; i ++) {
      for (int j = 0; j < width; j++) {
        int monochrome = this.imageSource[i][j] / divisor;
        int rgb = monochrome << 16;
        rgb += monochrome << 8;
        rgb += monochrome;
        this.bufferedImage.setRGB(j, i, rgb);
      }
    }
    this.writeImage(fileName);
  }

  private void writeImage(String fileName) {
    try {
      this.image = new File(fileName);
      ImageIO.write(this.bufferedImage, "png", image);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  public File getImage() {
    return this.image;
  }
}
