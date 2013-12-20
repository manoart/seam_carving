import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

import image_processing.ImageProcessor;

/**
 * A test class for the image processing. Mainly to read *.jpg images and test if the
 * RGB values are correct.
 */
public class ImageProcessingTest {

  public static void main(String[] argv) throws IOException {
    File imageSource;
    if (argv.length > 0) {
      imageSource = new File(argv[0]);
    } else {
      imageSource = new File("../images/test.jpg");
    }
    BufferedImage bufferedImage = ImageIO.read(imageSource);

    ImageProcessor imageProcessor = new ImageProcessor(bufferedImage);

    int[][] imageValues = imageProcessor.getImageArray();

    for (int i = 0; i < imageValues.length; i++) {
      for (int j = 0; j < imageValues[0].length; j++) {
        int red = (imageValues[i][j] >> 16) & 0xFF;
        int green = (imageValues[i][j] >> 8) & 0xFF;
        int blue = (imageValues[i][j]) & 0xFF;
        System.out.println(red + ", " + green + ", " + blue);
      }
    }
  }
}
