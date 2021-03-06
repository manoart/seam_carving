package image_processing;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class ImageProcessor {

  /** The given image. */
  private BufferedImage image;

  /** 2D array with int RGB values of the given image. */
  private int[][] imageSource;

  /** 3D array with separate RGB values of the given image. */
  private int[][][] imageRGBValues;

  /**
   * Default constructor;
   */
  public ImageProcessor() {
    this(null);
  }

  /**
   * Custom constructor.
   *
   * @param image The given Image that should be analyzed.
   */
  public ImageProcessor(BufferedImage image) {
    this.setImage(image);
    this.imageSource = new int[this.image.getHeight()][this.image.getWidth()];
    this.imageRGBValues = new int[this.image.getHeight()][this.image.getWidth()][3];
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
   * Getter for the image (RGB) values.
   *
   * @return 3D array with RGB and Alpha values for every pixel of the image.
   */
  public int[][][] getImageValues() {
    return this.getImageRGBValues();
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
  public int[][][] getImageRGBValues() {
    for (int y = 0; y < this.imageRGBValues.length; y++) {
      for (int x = 0; x < this.imageRGBValues[0].length; x++) {
        int rgb = this.image.getRGB(x, y);
        this.imageRGBValues[y][x][0] = (rgb >> 16) & 0xFF;  // red
        this.imageRGBValues[y][x][1] = (rgb >>  8) & 0xFF;  // green
        this.imageRGBValues[y][x][2] = (rgb      ) & 0xFF;  // blue
      }
    }
    return this.imageRGBValues;
  }

  public int[][] getImageSource() {
    for (int y = 0; y < this.imageSource.length; y++) {
      for (int x = 0; x < this.imageSource[0].length; x++) {
        this.imageSource[y][x] = this.image.getRGB(x, y);
      }
    }
    return this.imageSource;
  }


  /**
   * It does the same as the getImageArray() method but not as fast
   * (where it should be faster).
   * First dimension: rows.
   * Second dimension: columns.
   * Third dimension: RGB values.
   *
   * @return An 3 dimensional int array with RGB values for every pixel of the image.
   */
  private int[][][] getRGBValuesSlower() {

    byte[] pixels = ((DataBufferByte) this.image.getRaster().getDataBuffer()).getData();
    int width = this.image.getWidth();
    int height = this.image.getHeight();
    boolean hasAlphaChannel = this.image.getAlphaRaster() != null;
    int pixelLength;

    if (hasAlphaChannel) {
      pixelLength = 4;
    } else {
      pixelLength = 3;
    }
    this.imageRGBValues = new int[height][width][3];
    for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
      //result[row][col][3] = (((int) pixels[pixel]     & 0xff) << 24); // alpha
      this.imageRGBValues[row][col][2] = ( (int) pixels[pixel + 0]      ) & 0xFF;        // blue
      this.imageRGBValues[row][col][1] = (((int) pixels[pixel + 1] >>  8) & 0xFF); // green
      this.imageRGBValues[row][col][0] = (((int) pixels[pixel + 2] >> 16) & 0xFF); // red
      col++;
      if (col == width) {
        col = 0;
        row++;
      }
    }
    return this.imageRGBValues;
  }
}
