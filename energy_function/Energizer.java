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
    this();
  }

  /**
   * Custom constructor.
   *
   * @param imageRGBValues 
  public Energizer(int[][][] imageRGBValues) {
    this.imageRGBValues = imageRGBValues;
  }

  public int[][] getBrightness() {
    return this.calculateBrightness();
  }

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
