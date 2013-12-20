package image_processing;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessor {

  /** The given image. */
  private BufferedImage image;

  /** 2D array with RGB values of the given image. */
  private int[][][] imageValues;

  /**
   * Default constructor;
   */
  public ImageProcessor() {
    this.image = null;
  }

  /**
   * Custom constructor.
   *
   * @param image The given Image that should be analyzed.
   */
  public ImageProcessor(BufferedImage image) {
    this.setImage(image);
    this.imageValues = new int[this.image.getHeight()][this.image.getWidth()][3];
  }

  /**
   * Method to set the (buffered) image.
   *
   * @param image The Image that is to be analyzed.
   */
  public void setImage(BufferedImage image) {
    this.image = image;
  }

  /**
   * This method takes an image and converts it into an 3 dimensional int array in
   * which the RGB value of every pixel is stored.
   * First dimension: rows.
   * Second dimension: columns.
   * Third dimension: RGB values.
   *
   * @return An 3 dimensional int array with RGB values for every pixel of the image.
   */
  public int[][][] getImageArray() {
    for (int y = 0; y < this.imageValues.length; y++) {
      for (int x = 0; x < this.imageValues[0].length; x++) {
        // TODO change getRGB() to something faster!
        int rgb = this.image.getRGB(x, y);
        for (int color = 0; color < 3; color++) {
          this.imageValues[y][x][0] = (rgb >> 16) & 0xFF;
          this.imageValues[y][x][1] = (rgb >>  8) & 0xFF;
          this.imageValues[y][x][2] = (rgb      ) & 0xFF;
        }
      }
    }

    return this.imageValues;
  }
}
