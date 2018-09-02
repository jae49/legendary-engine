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

    public static boolean equalsMatrix(double[][] a, double[][] b) {
        int rows_a = rows(a);
        int cols_a = cols(a);
        if (rows_a != rows(b)) return false;
        if (cols_a != cols(b)) return false;
        for (int i=0; i < rows_a; i++) {
            for (int j = 0; j < cols_a; j++) {
                if (Math.abs(a[i][j] - b[i][j]) > Tuple.epsilon) return false;
            }
        }
        return true;
    }

    public static double[][] multiply(double[][] a, double[][] b) {
        if (cols(a) != rows(b)) {
            throw new IllegalArgumentException("Cannot multiply matrices: " +
                    "Columns in A do not match Rows in B");
        }
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
        int rows_b2 = rows(b2);
        for (int row = 0; row < rows_b2; row++) {
            b2[row][0] = tuple[row];
        }
        return transpose(multiply(matrix, b2))[0];
    }

    public static double[][] identity(int size) {
        double[][] a = squareMatrix(size);
        for (int i=0; i < size; i++) {
            a[i][i] = 1;
        }
        return a;
    }

    public static double[][] transpose(double[][] a) {
        int rows = rows(a);
        int cols = cols(a);
        double[][] b = new double[cols][rows];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                b[col][row] = a[row][col];
            }
        }
        return b;
    }

    public static double determinant(double[][] a) {
        int size = size(a);
        if (size == 2) return a[0][0] * a[1][1] - a[0][1] * a[1][0];
        double sum = 0;
        for (int i=0; i < size; i++) {
            sum += a[0][i] * cofactor(a, 0, i);
        }
        return sum;
    }

    public static double[][] subMatrix(double[][] a, int uRow, int uCol) {
        int size = size(a);
        double[][] sub = new double[size-1][size-1];
        for (int row = 0; row < size - 1; row++) {
            for (int col = 0; col < size - 1; col++) {
                int frow = row < uRow ? row : row + 1;
                int fcol = col < uCol ? col : col + 1;
                sub[row][col] = a[frow][fcol];
            }
        }
        return sub;
    }

    public static double minor(double[][] a, int row, int col) {
        return determinant(subMatrix(a, row, col));
    }

    public static double cofactor(double[][] a, int row, int col) {
        return (row + col) % 2 == 0 ? minor(a, row, col): -minor(a, row, col);
    }

    public static boolean isInvertible(double[][] a) {
        //if determinant is 0 (or close enough), it's not invertible
        return !(Math.abs(determinant(a)) < Tuple.epsilon);
    }

    public static double[][] inverse(double[][] a) {
        int rows = rows(a);
        int cols = cols(a);
        double[][] cofactors = new double[rows][cols];

        //construct cofactors for each element
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cofactors[row][col] = cofactor(a, row, col);
            }
        }

        return divide(transpose(cofactors), determinant(a));
    }

    private static double[][] divide(double[][] matrix, double scalar) {
        int rows = rows(matrix);
        int cols = cols(matrix);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = matrix[row][col] / scalar;
            }
        }
        return matrix;
    }


}
