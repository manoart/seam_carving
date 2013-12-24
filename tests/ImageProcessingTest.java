import image_processing.ImageReader;
import image_processing.ImageProcessor;

/**
 * A test class for the image processing. Mainly to read *.jpg images and test if the
 * RGB values are correct.
 */
public class ImageProcessingTest {

  public static void main(String[] argv) {
    ImageReader imageReader;
    if (argv.length > 0) {
      imageReader = new ImageReader(argv[0]);
    } else {
      imageReader = new ImageReader("../images/test.jpg");
    }
    ImageProcessor imageProcessor = new ImageProcessor(imageReader.getBufferedImage());

    long startTime = System.nanoTime();
    int[][][] imageValues = imageProcessor.getImageValues();
    long endTime = System.nanoTime();

//    long time = endTime - startTime;
//    System.out.println("Time: " + time);

    for (int i = 0; i < imageValues.length; i++) {
      for (int j = 0; j < imageValues[0].length; j++) {
        int red = imageValues[i][j][0];
        int green = imageValues[i][j][1];
        int blue = imageValues[i][j][2];
        System.out.println(red + ", " + green + ", " + blue);
      }
    }

  }
}
