package seam_carving;

import java.util.List;
import java.util.ArrayList;

import seam_carving.Seam;
import energy_function.*;

public class SeamGenerator {

  private int[][] energy;

  private int[][] imageSource;

  private int[][][] cumulatedEnergy;

  private List<Seam> seams;

  private int seamCount;

  public SeamGenerator() {
    this(null, null, 0);
  }

  public SeamGenerator(int[][] imageSource, int[][] energy, int seamCount) {
    this.imageSource = imageSource;
    this.energy = energy;
    this.seamCount = seamCount;
    this.seams = new ArrayList<Seam>(seamCount);
    this.cumulatedEnergy = new int[this.energy.length][this.energy[0].length][2];
    this.calculateCumulatedEnergy();
  }

  public int[][] getEnergy() {
    return this.energy;
  }

  public int[][][] getCumulatedEnergy() {
    return this.cumulatedEnergy;
  }

  public List<Seam> getSeams() {
    this.generateSeams();
    return this.seams;
  }

  public Object[] getSeamsArray() {
    return this.seams.toArray();
  }

  private void generateSeams() {
    //find the seam with the lowest energy (minimal value in the last row)
    for (int s = 0; s < this.seamCount; s++) {
      int lowestEnergy = Integer.MAX_VALUE;
      int lowestEnergyIndex = 0;
      for (int j = 0; j < this.energy[0].length; j++) {
        if (this.cumulatedEnergy[this.energy.length - 1][j][0] < lowestEnergy) {
          lowestEnergy = this.cumulatedEnergy[this.energy.length - 1][j][0];
          lowestEnergyIndex = j;
        }
      }

      Seam seam = this.generateSeam(lowestEnergyIndex);
      //this.cumulatedEnergy[this.cumulatedEnergy.length - 1][lowestEnergyIndex][0] = Integer.MAX_VALUE;
   //   if (!seams.contains(seam)) {
        seams.add(seam);
     // }
      this.reduceEnergyArray();
      this.cumulatedEnergy = new int[this.energy.length][this.energy[0].length][2];
      this.calculateCumulatedEnergy();
    }
  }

  private Seam generateSeam(int lowestEnergyIndex) {
    Seam seam;
    int[] path = new int[this.cumulatedEnergy.length];
    double colorNuance;
    int tmp = lowestEnergyIndex;

    // find the path
    int i;
    for (i = this.cumulatedEnergy.length - 1; i > 0; i--) {
      path[i - 1] = - this.cumulatedEnergy[i][tmp][1];
      this.cumulatedEnergy[i][tmp][0] = Integer.MAX_VALUE;
      tmp += this.cumulatedEnergy[i][tmp][1];
    }
    this.cumulatedEnergy[i][tmp][0] = Integer.MAX_VALUE;

    // find the color
    colorNuance = 255.0 / this.seamCount; // just for testing

    int startPoint;
    if (this.seams.size() > 0 && this.seams.get(this.seams.size() - 1).getStartPoint() <= tmp) {
      startPoint = tmp + this.seams.size();
    } else {
      startPoint = tmp;
    }
    // color = (int)(255.0 - (colorNuance * this.seams.size())
    seam = new Seam(startPoint, path, 255);
    return seam;
  }

  private void reduceEnergyArray() {
    int[][] imageNew = new int[this.imageSource.length][this.imageSource[0].length - 1];
    int[][] energyNew = new int[this.energy.length][this.energy[0].length - 1];
    int columnOffset = 0;
    for (int i = 0; i < energyNew.length; i++) {
      columnOffset = 0;
      for (int j = 0; j < energyNew[0].length; j++) {
        if(this.cumulatedEnergy[i][j][0] == Integer.MAX_VALUE) {
          columnOffset = 1;
        }
        imageNew[i][j] = this.imageSource[i][j + columnOffset];
        energyNew[i][j] = this.energy[i][j + columnOffset];
      }
    }
    this.imageSource = imageNew;
    this.energy = energyNew;
  }

  public int[][] getImageSource() {
    return this.imageSource;
  }

  private void calculateCumulatedEnergy() {
    // filling the first row (identical to this.energy)
    for (int row = 0; row < this.energy[0].length; row++) {
      this.cumulatedEnergy[0][row][0] = this.energy[0][row];
    }

    for (int i = 1; i < this.cumulatedEnergy.length; i++) {
      for (int j = 0; j < this.cumulatedEnergy[0].length; j++) {
        int minEnergy = Integer.MAX_VALUE;
        int energy = this.energy[i][j];
        int direction = 0;

        // TODO refactor!
        if (j == 0) {
          if ((energy + this.cumulatedEnergy[i - 1][j][0]) < minEnergy) {
            minEnergy = energy + this.cumulatedEnergy[i - 1][j][0];
            direction = 0;
          }
          if ((energy + this.cumulatedEnergy[i - 1][j + 1][0]) < minEnergy) {
            minEnergy = energy + this.cumulatedEnergy[i - 1][j + 1][0];
            direction = 1;
          }
          this.cumulatedEnergy[i][j][0] = minEnergy;
          this.cumulatedEnergy[i][j][1] = direction;

        } else if (j == this.cumulatedEnergy[0].length - 1) {
          if ((energy + this.cumulatedEnergy[i - 1][j - 1][0]) < minEnergy) {
            minEnergy = energy + this.cumulatedEnergy[i - 1][j - 1][0];
            direction = -1;
          }
          if ((energy + this.cumulatedEnergy[i - 1][j][0]) < minEnergy) {
            minEnergy = energy + this.cumulatedEnergy[i - 1][j][0];
            direction = 0;
          }
          this.cumulatedEnergy[i][j][0] = minEnergy;
          this.cumulatedEnergy[i][j][1] = direction;

        } else {
          if ((energy + this.cumulatedEnergy[i - 1][j - 1][0]) < minEnergy) {
            minEnergy = energy + this.cumulatedEnergy[i - 1][j - 1][0];
            direction = -1;
          }
          if ((energy + this.cumulatedEnergy[i - 1][j][0]) < minEnergy) {
            minEnergy = energy + this.cumulatedEnergy[i - 1][j][0];
            direction = 0;
          }
          if ((energy + this.cumulatedEnergy[i - 1][j + 1][0]) < minEnergy) {
            minEnergy = energy + this.cumulatedEnergy[i - 1][j + 1][0];
            direction = 1;
          }
          this.cumulatedEnergy[i][j][0] = minEnergy;
          this.cumulatedEnergy[i][j][1] = direction;

        }
      }
    }
  }
}
