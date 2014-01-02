package seam_carving;

import java.util.List;
import java.util.ArrayList;

import java.awt.image.BufferedImage;

public class SeamCarver {

  private BufferedImage input;

  private BufferedImage output;

  private BufferedImage seamImage;

  private List<Seam> seams;

  public SeamCarver() {
    this(null, null);
  }

  public SeamCarver(BufferedImage input, List<Seam> seams) {
    this.input = input;
    this.seams = seams;
    this.output = new BufferedImage(this.input.getWidth(),// - this.seams.size(),
                                    this.input.getHeight(),
                                    BufferedImage.TYPE_INT_RGB);
  }

  public BufferedImage getOutput() {
    this.generateOutput();
    return this.output;
  }

  private void generateOutput() {
    for (int y = 0; y < this.input.getHeight(); y++) {
      int xOffset = 0;
      for (Seam seam: this.seams) {
        int[] path = seam.getPath();
        int startPoint = seam.getStartPoint();
        int xCompare = startPoint + xOffset;
        for (int i = 0; i < y; i++) {
          xCompare += path[i];
        }
        for (int x = 0; x < this.input.getWidth(); x++) {
          if (x == xCompare) {
            xOffset--;
            continue;
          } else {
            int color = this.input.getRGB(x, y);
            this.output.setRGB(x + xOffset, y, color);
          }
        }
      }
    }
  }
}
