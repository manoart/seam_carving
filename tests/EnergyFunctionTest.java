package tests;

import image_processing.*;
import energy_function.Energizer;

public class EnergyFunctionTest {

  public static void main(String[] argv) {
    ImageReader imageReader;
    if (argv.length > 0) {
      imageReader = new ImageReader(argv[0]);
    } else {
      imageReader = new ImageReader("../images/test.jpg");
    }
    ImageProcessor imageProcessor = new ImageProcessor(imageReader.getBufferedImage());

    int[][][] imageValues = imageProcessor.getImageValues();

    Energizer energizer = new Energizer(imageValues);

    int[][] brightness = energizer.getBrightness();

    for (int i = 0; i < brightness.length; i++) {
      for (int j = 0; j < brightness[0].length; j++) {
        System.out.println(brightness[i][j]);
      }
    }
  }
}
