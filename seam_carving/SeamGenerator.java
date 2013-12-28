package seam_carving;

import java.util.List;
import java.util.ArrayList;

import seam_carving.Seam;
import energy_function.*;

public class SeamGenerator {

  private int[][] energy;

  private int[][][] cumulatedEnergy;

  private List<Seam> seams;

  public SeamGenerator() {
    this(null, 0);
  }

  public SeamGenerator(int[][] energy, int seamNumber) {
    this.energy = energy;
    this.seams = new ArrayList<Seam>(seamNumber);
    this.cumulatedEnergy = new int[this.energy.length][this.energy[0].length][2];
  }

  public List<Seam> getSeams() {
    this.generateSeams();
    return this.seams;
  }

  public Object[] getSeamsArray() {
    return this.seams.toArray();
  }

  private void generateSeams() {
    this.calculateCumulatedEnergy();
    this.addSeams();
  }

  private void addSeams() {
    List indices = new ArrayList(this.seams.size());
    //find the seam with the lowest energy (minimal value in the last row)
    int lowestEnergy = Integer.MAX_VALUE;
    int lowestEnergyIndex = 0;
    for (int j = 0; j < this.energy[0].length; j++) {
      if (this.cumulatedEnergy[this.energy.length - 1][j][0] < lowestEnergy) {
        lowestEnergy = this.cumulatedEnergy[this.energy.length - 1][j][0];
        lowestEnergyIndex = j;
      }
    }

    Seam seam = this.generateSeam(lowestEnergyIndex);
    if (!seams.contains(seam)) {
      seams.add(seam);
    }
  }

  private Seam generateSeam(int lowestEnergyIndex) {
    Seam seam;
    int[] path = new int[this.cumulatedEnergy.length];
    int color;
    int tmp = lowestEnergyIndex;

    // find the path
    for (int i = this.cumulatedEnergy.length - 1; i > 0; i--) {
      path[i - 1] = - this.cumulatedEnergy[i][tmp][1];
    }

    // find the color
    // TODO calculate it properly
    color = 255; // just for testing
    //color = this.cumulatedEnergy[this.cumulatedEnergy.length - 1][lowestEnergyIndex][0];

    seam = new Seam(tmp, path, color);
    return seam;
  }

  private void calculateCumulatedEnergy() {
    // filling the first row (identical to this.energy)
    for (int row = 0; row < this.energy[0].length; row++) {
      this.cumulatedEnergy[0][row][0] = this.energy[0][row];
    }

    for (int i = 1; i < this.energy.length; i++) {
      for (int j = 0; j < this.energy[0].length; j++) {
        int minEnergy = Integer.MAX_VALUE;
        int energy = this.energy[i][j];
        int direction = 0;

        // TODO refactor!
        if (j == 0) {
          if ((energy + this.energy[i - 1][j]) < minEnergy) {
            minEnergy = energy + this.energy[i - 1][j];
            direction = 0;
          } else if ((energy + this.energy[i - 1][j + 1]) < minEnergy) {
            minEnergy = energy + this.energy[i - 1][j + 1];
            direction = 1;
          }
          this.cumulatedEnergy[i][j][0] = minEnergy;
          this.cumulatedEnergy[i][j][1] = direction;

        } else if (j == this.energy[0].length - 1) {
          if ((energy + this.energy[i - 1][j - 1]) < minEnergy) {
            minEnergy = energy + this.energy[i - 1][j - 1];
            direction = -1;
          } else if ((energy + this.energy[i - 1][j]) < minEnergy) {
            minEnergy = energy + this.energy[i - 1][j];
            direction = 0;
          }
          this.cumulatedEnergy[i][j][0] = minEnergy;
          this.cumulatedEnergy[i][j][1] = direction;

        } else {
          if ((energy + this.energy[i - 1][j - 1]) < minEnergy) {
            minEnergy = energy + this.energy[i - 1][j - 1];
            direction = -1;
          } else if ((energy + this.energy[i - 1][j]) < minEnergy) {
            minEnergy = energy + this.energy[i - 1][j];
            direction = 0;
          } else if ((energy + this.energy[i - 1][j + 1]) < minEnergy) {
            minEnergy = energy + this.energy[i - 1][j + 1];
            direction = 1;
          }
          this.cumulatedEnergy[i][j][0] = minEnergy;
          this.cumulatedEnergy[i][j][1] = direction;

        }
      }
    }
  }
}
