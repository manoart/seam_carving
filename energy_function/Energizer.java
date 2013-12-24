package energy_function;

public class Energizer {

  /** Array with the brightness values for each pixel. */
  private int[][] brightness;

  /** Array with the energy values for each pixel. */
  private int[][] energy;

  /** Array with the RGB values for each pixel. */
  private int[][][] imageRGBValues;


  /**
   * Default constructor.
   */
  public Energizer() {
    this(null);
  }


  /**
   * Custom constructor.
   *
   * @param imageRGBValues 3D Array with the RGB values of the image.
   */
  public Energizer(int[][][] imageRGBValues) {
    this.imageRGBValues = imageRGBValues;
    this.brightness = new int[this.imageRGBValues.length][this.imageRGBValues[0].length];
    this.energy = new int[this.imageRGBValues.length][this.imageRGBValues[0].length];
  }


  /**
   * Getter for the brightness array. This array is used to calculate the
   * energy of every given pixel of an image.
   *
   * @return 2D array with the brightness values of every pixel.
   */
  public int[][] getBrightness() {
    return this.calculateBrightness();
  }


  /**
   * Getter for the energy array. This array is later used to calculate the
   * seams which should be carved out.
   *
   * @return 2D array with the energy values of every pixel.
   */
  public int[][] getEnergy() {
    return this.calculateEnergy();
  }


  /**
   * Calculates the brightness of each pixel (sum of the RGB values)
   * of an image.
   *
   * @return 2D array of the image with brightness values for each pixel.
   */
  private int[][] calculateBrightness() {
    for (int i = 0; i < this.imageRGBValues.length; i++) {
      for (int j = 0; j < this.imageRGBValues[0].length; j++) {
        int brightness = 0;
        for (int color = 0; color < 3; color++) {
          brightness += this.imageRGBValues[i][j][color];
        }
        this.brightness[i][j] = brightness;
      }
    }
    return this.brightness;
  }


  /**
   * Computes the energy of a pixel E surrounded by pixels A through I,
   *
   *       A B C
   *       D E F
   *       G H I
   *
   * using the formula
   *       energy(E) = sqrt(xenergy^2 + yenergy^2)
   * where
   *       xenergy = a + 2d + g - c - 2f - i
   *       yenergy = a + 2b + c - g - 2h - i
   *
   * and each lowercase letter represents the brightness
   * (sum of the red, blue, and green values) of the corresponding pixel.
   *
   * Edge pixels are calculated pretending that the image is surrounded
   * by a 1 pixel wide border of black pixels (with 0 brightness).
   *
   * @return 2D array with the energy value vor each pixel.
   */
  private int[][] calculateEnergy() {
    return this.energy;
  }

}
