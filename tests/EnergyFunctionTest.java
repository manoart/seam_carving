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

    long startTime = System.nanoTime();
    ImageProcessor imageProcessor = new ImageProcessor(imageReader.getBufferedImage());

    int[][][] imageValues = imageProcessor.getImageValues();

    Energizer energizer = new Energizer(imageValues);

    int[][] brightness = energizer.getBrightness();

    int[][] energy = energizer.getEnergy();

//    System.out.println("Brightness:");
//    for (int i = 0; i < brightness.length; i++) {
//      for (int j = 0; j < brightness[0].length; j++) {
//        System.out.println(brightness[i][j]);
//      }
//    }

//    System.out.println();
//    System.out.println("Energy:");
//    for (int i = 0; i < energy.length; i++) {
//      for (int j = 0; j < energy[0].length; j++) {
//        System.out.println(energy[i][j]);
//      }
//    }

//    ImageWriter imageBrightnessWriter = new ImageWriter(brightness);
//    imageBrightnessWriter.writeBrightnessImage();

    ImageWriter imageEnergyWriter = new ImageWriter(energy);
    imageEnergyWriter.writeEnergyImage();

    long endTime = System.nanoTime();

    System.out.println("Done. Time: " + (endTime - startTime));
  }
}
