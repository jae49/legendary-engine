package net.rednode.rtc;

public class Matrix {

    public static double[][] matrix(int rows, int cols) {
        return new double[rows][cols];
    }

    public static double[][] squareMatrix(int size) {
        return matrix(size, size);
    }

    public static void setRow(double[][] matrix, int row, double[] values) {
        System.arraycopy(values, 0, matrix[row], 0, values.length);
    }

    public static int rows(double[][] matrix) {
        return matrix.length;
    }

    public static int cols(double[][] matrix) {
        return matrix[0].length;
    }

    public static int size(double[][] matrix) {
        if (rows(matrix) == cols(matrix)) return rows(matrix);
        throw new IllegalArgumentException("matrix is not square");
    }

    public static double[][] multiply(double[][] a, double[][] b) {
        double[][] c = new double[rows(a)][cols(b)];
        int c_rows = rows(c);
        int c_cols = cols(c);
        int common = rows(b);
        for (int i = 0; i < c_rows; i++) {
            for (int j = 0; j < c_cols; j++) {
                c[i][j] = 0;
                for (int m = 0; m < common; m++) c[i][j] += a[i][m] * b[m][j];
            }
        }
        return c;
    }

    public static double[] multiply(double[][] matrix, double[] tuple) {
        double[][] b2 = matrix(tuple.length, 1);
        for (int row = 0; row < rows(b2); row++) {
            b2[row][0] = tuple[row];
        }
        return reorient(multiply(matrix, b2));
    }

    private static double[] reorient(double[][] vtuple) {
        double[] result = new double[vtuple.length];
        for (int i=0; i < vtuple.length; i++) result[i] = vtuple[i][0];
        return result;
    }


}
