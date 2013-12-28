package seam_carving;

public class Seam {

  /** x-coordinate of the seam's starting point at the top. */
  private int startPoint;

  /** Contains the values {-1, 0, 1} for every row of the image. */
  private int[] path;

  /** The int RGB value of the Seam as a shade of red. */
  private int color;

  /**
   * Default constructor.
   */
  public Seam() {
    this(0, null, 0);
  }

  /**
   * Custom constructor.
   *
   * @param startPoint The x-coordinate of the seam's starting
   *                   Point at the top of the image.
   */
  public Seam(int startPoint) {
    this(startPoint, null, 0);
  }

  /**
   * Custom constructor.
   *
   * @param startPoint The x-coordinate of the seam's starting
   *                   point at the top of the image.
   * @param path The path of the seam from top to bottom.
   *             Contains the values {-1, 0, 1} for every row.
   * @param color The red value of the seam's color (0-255).
   */
  public Seam(int startPoint, int[] path, int color) {
    this.startPoint = startPoint;
    this.path = path;
    this.setColor(color);
  }

  /**
   * Getter for the seam's starting point.
   *
   * @return The seam's starting point at the top of the image.
   */
  public int getStartPoint() {
    return this.startPoint;
  }

  /**
   * Getter for the seam's path.
   *
   * @return The path of a seam from top to bottom, containing
   *         the values {-1, 0, 1} for {left, middle, right}.
   */
  public int[] getPath() {
    return this.path;
  }

  /**
   * Getter for the seam's color.
   *
   * @return A valid int RGB color for the seam (in this case
   *         a shade of red).
   */
  public int getColor() {
    return this.color;
  }

  /**
   * Setter for the seam's path.
   *
   * @param path The seam's path.
   */
  public void setPath(int[] path) {
    this.path = path;
  }

  /**
   * Setter for the Seam's color. Calculates a valid int RGB value.
   *
   * @param color The red value (0-255) for the Seam.
   */
  private void setColor(int color) {
    this.color = color << 16;
  }

  @Override
  public boolean equals(Object seam) {
    return (this.startPoint == ((Seam) seam).getStartPoint());
  }
}
