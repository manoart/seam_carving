package tests;

import seam_carving.*;
import energy_function.*;
import image_processing.*;

import java.util.List;
import java.util.ArrayList;

public class SeamCarverTest {

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

    Energizer energizer = new Energizer(imageValues);

    int[][] brightness = energizer.getBrightness();

    int[][] energy = energizer.getEnergy();

//    ImageWriter imageBrightnessWriter = new ImageWriter(brightness);
//    imageBrightnessWriter.writeBrightnessImage();

//    ImageWriter imageEnergyWriter = new ImageWriter(energy);
//    imageEnergyWriter.writeEnergyImage();

    SeamGenerator sg = new SeamGenerator(energy, 5);

    ImageWriter imageSeamWriter = new ImageWriter(energy);
    List<Seam> seams = sg.getSeams();
    imageSeamWriter.writeSeamImage(seams);

    SeamCarver seamCarver = new SeamCarver(imageReader.getBufferedImage(),
                                           seams);

    ImageWriter imageOutputWriter = new ImageWriter(seamCarver.getOutput());
    imageOutputWriter.writeOutputImage();

    long endTime = System.nanoTime();

    System.out.println("Done. Time: " + (endTime - startTime));
  }
}
