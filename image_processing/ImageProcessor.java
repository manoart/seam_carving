package image_processing;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessor {

  /** The given image. */
  private BufferedImage image;

  /** 2D array with RGB values of the given image. */
  private int[][] imageValues;

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
    this.imageValues = new int[this.image.getHeight()][this.image.getWidth()];
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }

  public int[][] getImageArray() {
    for (int y = 0; y < this.imageValues.length; y++) {
      for (int x = 0; x < this.imageValues[0].length; x++) {
        this.imageValues[y][x] = this.image.getRGB(x, y);
      }
    }

    return this.imageValues;
  }
}
