package inc.premzl.dht.Matrix;

public class MatrixOperations {
    public static double[][][] multiplyMatrices(double[][][] first, double[][][] second) {
        double[][][] result = new double[first.length][second[0].length][3];

        for (int channel = 0; channel < 3; channel++) {
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    result[i][j][channel] = multiplyCell(first, second, channel, i, j);
                }
            }
        }

        return result;
    }

    public static double multiplyCell(double[][][] first, double[][][] second, int channel, int row, int col) {
        double cell = 0;

        for (int i = 0; i < second.length; i++) {
            cell += first[row][i][channel] * second[i][col][channel];
        }

        return cell;
    }

    public static double[][][] transpose(double[][][] matrix) {
        double[][][] result = new double[matrix[0].length][matrix.length][3];

        for (int channel = 0; channel < 3; channel++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    result[j][i][channel] = matrix[i][j][channel];
                }
            }
        }

        return result;
    }

    public static double[][][] getHaarMatrix() {
        double root1 = Math.sqrt(8.0 / 64.0), root2 = Math.sqrt(2.0 / 4.0);
        double[][][] rgbHaar = new double[8][8][3];
        double[][] haar = new double[][]{
                new double[]{root1, root1, root1, root1, root1, root1, root1, root1},
                new double[]{root1, root1, root1, root1, -root1, -root1, -root1, -root1},
                new double[]{1.0 / 2.0, 1.0 / 2.0, -1.0 / 2.0, -1.0 / 2.0, 0, 0, 0, 0},
                new double[]{0, 0, 0, 0, 1.0 / 2.0, 1.0 / 2.0, -1.0 / 2.0, -1.0 / 2.0},
                new double[]{root2, -root2, 0, 0, 0, 0, 0, 0},
                new double[]{0, 0, root2, -root2, 0, 0, 0, 0},
                new double[]{0, 0, 0, 0, root2, -root2, 0, 0},
                new double[]{0, 0, 0, 0, 0, 0, root2, -root2},
        };

        for (int channel = 0; channel < 3; channel++) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    rgbHaar[i][j][channel] = haar[i][j];
                }
            }
        }

        return rgbHaar;
    }
}
