package tests;

import seam_carving.*;
import energy_function.*;
import image_processing.*;

import java.util.List;
import java.util.ArrayList;

public class SeamGeneratorTest {

  public static void main(String [] argv) {
    ImageReader imageReader;
    if (argv.length > 0) {
      imageReader = new ImageReader(argv[0]);
    } else {
      imageReader = new ImageReader("../images/test.jpg");
    }

    long startTime = System.nanoTime();
    ImageProcessor imageProcessor = new ImageProcessor(imageReader.getBufferedImage());

    int[][][] imageValues = imageProcessor.getImageValues();
    int[][] imageSource = imageProcessor.getImageSource();

    Energizer energizer = new Energizer(imageValues);

    int[][] brightness = energizer.getBrightness();

    int[][] energy = energizer.getEnergy();

//    int[][] energy = {{1,4,3,5,2}, {3,2,5,2,3}, {5,2,4,2,1}};

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

//    ImageWriter imageEnergyWriter = new ImageWriter(energy);
//    imageEnergyWriter.writeEnergyImage();

    SeamGenerator sg = new SeamGenerator(imageSource, energy, 600);

//    int[][][] cumulatedEnergy = sg.getCumulatedEnergy();

    ImageWriter imageSeamWriter = new ImageWriter(energy);
    List<Seam> seams = sg.getSeams();

//    int[][][] cumulatedEnergy = sg.getCumulatedEnergy();

//    for (Seam seam: seams) {
//      int[] path = seam.getPath();
//      int startPoint = seam.getStartPoint();
//      System.out.println("Seam (" + startPoint + "): ");
//      for (int i = 0; i < path.length; i++) {
//        System.out.print(path[i] + "  ");
//      }
//      System.out.println();
//    }

    imageSeamWriter.writeSeamImage(seams);

    imageSource = sg.getImageSource();
    ImageWriter imageSourceWriter = new ImageWriter(imageSource);
    imageSourceWriter.writeOutputImage();

//    energy = sg.getEnergy();
//    ImageWriter imageEnergyWriter = new ImageWriter(energy);
//    imageEnergyWriter.writeEnergyImage();

//    System.out.println();
//    System.out.println("Energy:");
//    for (int i = 0; i < energy.length; i++) {
//      for (int j = 0; j < energy[0].length; j++) {
//        System.out.print(energy[i][j] + "  ");
//      }
//      System.out.println();
//    }
//    System.out.println();
//    System.out.println("CumulatedEnergy:");
//    for (int i = 0; i < cumulatedEnergy.length; i++) {
//      for (int j = 0; j < cumulatedEnergy[0].length; j++) {
//        System.out.print(cumulatedEnergy[i][j][0] + "  ");
//      }
//      System.out.println();
//    }

    long endTime = System.nanoTime();

    System.out.println("Done. Time: " + (endTime - startTime));
  }
}
