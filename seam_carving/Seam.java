package seam_carving;

public class Seam {

  /** x-coordinate of the seam's starting point at the top. */
  private int startPoint;

  /** Contains the values {-1, 0, 1} for every row of the image. */
  private int[] path;

  /**
   * Default constructor.
   */
  public Seam() {
    this(0, null);
  }

  /**
   * Custom constructor.
   *
   * @param startPoint The x-coordinate of the seam's starting
   *                   Point at the top of the image.
   */
  public Seam(int startPoint) {
    this(startPoint, null);
  }

  /**
   * Custom constructor.
   *
   * @param startPoint The x-coordinate of the seam's starting
   *                   point at the top of the image.
   * @param path The path of the seam from top to bottom.
   *             Contains the values {-1, 0, 1} for every row.
   */
  public Seam(int startPoint, int[] path) {
    this.startPoint = startPoint;
    this.path = path;
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
  public int[] getPath() {
    return this.path;
  }
}
