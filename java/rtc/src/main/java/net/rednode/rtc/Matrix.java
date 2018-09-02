package net.rednode.rtc;

public class Matrix {

    public static double[][] matrix(int rows, int cols) {
        return new double[rows][cols];
    }

    public static double[][] squareMatrix(int size) {
        return matrix(size, size);
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

}
