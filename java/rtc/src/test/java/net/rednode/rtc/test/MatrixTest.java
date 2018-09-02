package net.rednode.rtc.test;

import net.rednode.rtc.Tuple;
import org.junit.Test;

import static net.rednode.rtc.Matrix.*;
import static net.rednode.rtc.Tuple.tuple;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MatrixTest {

    @Test
    public void test4x4MatrixCreation() {
        double[][] m = squareMatrix(4);
        setRow(m, 0, new double[] {1, 2, 3, 4});
        setRow(m, 1, new double[] {5.5, 6.5, 7.5, 8.5});
        setRow(m, 2, new double[] {9, 10, 11, 12});
        setRow(m, 3, new double[] {13.5, 14.5, 15.5, 16.5});
        assertEquals(m[0][0], 1, Tuple.epsilon);
        assertEquals(m[0][3], 4, Tuple.epsilon);
        assertEquals(m[1][0], 5.5, Tuple.epsilon);
        assertEquals(m[1][2], 7.5, Tuple.epsilon);
        assertEquals(m[2][2], 11, Tuple.epsilon);
        assertEquals(m[3][0], 13.5, Tuple.epsilon);
        assertEquals(m[3][2], 15.5, Tuple.epsilon);
    }

    @Test
    public void test2x2Size() {
        double[][] m = squareMatrix(2);
        setRow(m, 0, new double[] {-3, 5});
        setRow(m, 1, new double[] {1, -2});
        assertEquals(2, size(m));
    }

    @Test
    public void test3x3Size() {
        double[][] m = squareMatrix(3);
        setRow(m, 0, new double[] {-3, 5, 0});
        setRow(m, 1, m[1] = new double[] {1, -2, -7});
        setRow(m, 2, new double[] {0, 1, 1});
        assertEquals(3, size(m));
    }

    @Test
    public void testMatrixEqualityMethod() {
        double[][] a = squareMatrix(4);
        double[][] b = squareMatrix(4);
        double[][] c = squareMatrix(4);
        double[][] d = matrix(4,3);
        setRow(a, 0, new double[] { 0, 1, 2, 4});
        setRow(a, 1, new double[] { 1, 2, 4, 8});
        setRow(a, 2, new double[] { 2, 4, 6, 8});
        setRow(a, 3, new double[] { 4, 8, 16, 32});

        setRow(b, 0, new double[] { 0, 1, 2, 4});
        setRow(b, 1, new double[] { 1, 2, 4, 8});
        setRow(b, 2, new double[] { 2, 4, 6, 8});
        setRow(b, 3, new double[] { 4, 8, 16, 32});

        setRow(c, 0, new double[] { 0, 1, 2, 4});
        setRow(c, 1, new double[] { 1, 2, 4, 8});
        setRow(c, 2, new double[] { 2, 3, 6, 8});
        setRow(c, 3, new double[] { 4, 8, 16, 32});

        setRow(d, 0, new double[] { 0, 1, 2});
        setRow(d, 1, new double[] { 1, 2, 4});
        setRow(d, 2, new double[] { 2, 4, 6});
        setRow(d, 3, new double[] { 4, 8, 16});

        assertTrue(equalsMatrix(a, b));
        assertFalse(equalsMatrix(b, c));
        assertFalse(equalsMatrix(b, d));
    }



    @Test
    public void testMultiplyMatrix4x4() {
        double[][] a = squareMatrix(4);
        double[][] b = squareMatrix(4);
        setRow(a, 0, new double[] {1, 2, 3, 4});
        setRow(a, 1, new double[] {2, 3, 4, 5});
        setRow(a, 2, new double[] {3, 4, 5, 6});
        setRow(a, 3, new double[] {4, 5, 6, 7});
        setRow(b, 0, new double[] {0, 1, 2, 4});
        setRow(b, 1, new double[] {1, 2, 4, 8});
        setRow(b, 2, new double[] {2, 4, 8, 16});
        setRow(b, 3, new double[] {4, 8, 16, 32});
        double[][] c = multiply(a, b);
        assertEquals(24, c[0][0],  Tuple.epsilon);
        assertEquals(49, c[0][1], Tuple.epsilon);
        assertEquals(98, c[0][2], Tuple.epsilon);
        assertEquals(196, c[0][3], Tuple.epsilon);
        assertEquals(31, c[1][0], Tuple.epsilon);
        assertEquals(64, c[1][1], Tuple.epsilon);
        assertEquals(128, c[1][2], Tuple.epsilon);
        assertEquals(256, c[1][3], Tuple.epsilon);
        assertEquals(38, c[2][0], Tuple.epsilon);
        assertEquals(79, c[2][1], Tuple.epsilon);
        assertEquals(158,c[2][2],  Tuple.epsilon);
        assertEquals(316, c[2][3], Tuple.epsilon);
        assertEquals(45, c[3][0], Tuple.epsilon);
        assertEquals(94, c[3][1], Tuple.epsilon);
        assertEquals(188, c[3][2], Tuple.epsilon);
        assertEquals(376, c[3][3], Tuple.epsilon);
    }

    @Test
    public void testMultiplyMatrix1x5times5x1() {
        double[][] a = matrix(1, 5);
        double[][] b = matrix(5, 1);
        setRow(a, 0, new double[] { 1, 2, 3, 4, 5});
        setRow(b, 0, new double[] { 1 });
        setRow(b, 1, new double[] { 2 });
        setRow(b, 2, new double[] { 3 });
        setRow(b, 3, new double[] { 4 });
        setRow(b, 4, new double[] { 5 });
        double[][] c = multiply(a, b);
        assertEquals(1, rows(c));
        assertEquals(1, cols(c));
        assertEquals(55, c[0][0], Tuple.epsilon);
    }

    @Test
    public void testMultiplyMatrix3x4times4x2() {
        double[][] a = matrix(3, 4);
        double[][] b = matrix(4, 2);
        setRow(a, 0, new double[] { 3, 1, 1, 4});
        setRow(a, 1, new double[] { 5, 3, 2, 1});
        setRow(a, 2, new double[] { 6, 2, 9, 5});
        setRow(b, 0, new double[] { 4, 9});
        setRow(b, 1, new double[] { 6, 8});
        setRow(b, 2, new double[] { 9, 7});
        setRow(b, 3, new double[] { 7, 6});
        double[][] c = multiply(a, b);
        assertEquals(55, c[0][0], Tuple.epsilon);
        assertEquals(66, c[0][1], Tuple.epsilon);
        assertEquals(63, c[1][0], Tuple.epsilon);
        assertEquals(89, c[1][1], Tuple.epsilon);
        assertEquals(152, c[2][0], Tuple.epsilon);
        assertEquals(163, c[2][1], Tuple.epsilon);
    }

    @Test
    public void testSquareTranspose() {
        double[][] a = squareMatrix(4);
        double[][] expected = squareMatrix(4);

        setRow(a, 0, new double[] { 0, 9, 3, 0});
        setRow(a, 1, new double[] { 9, 8, 0, 8});
        setRow(a, 2, new double[] { 1, 8, 5, 3});
        setRow(a, 3, new double[] { 0, 0, 5, 8});

        setRow(expected, 0, new double[] { 0, 9, 1, 0});
        setRow(expected, 1, new double[] { 9, 8, 8, 0});
        setRow(expected, 2, new double[] { 3, 0, 5, 5});
        setRow(expected, 3, new double[] { 0, 8, 3, 8});

        assertTrue(equalsMatrix(expected, transpose(a)));
    }

    @Test
    public void testNonSquareTranspose() {
        double[][] a = matrix(1, 4);
        double[][] expected = matrix(4, 1);

        setRow(a, 0, new double[] { 0, 9, 3, 0});

        setRow(expected, 0, new double[] { 0 });
        setRow(expected, 1, new double[] { 9 });
        setRow(expected, 2, new double[] { 3 });
        setRow(expected, 3, new double[] { 0 });

        double[][] transposed = transpose(a);

        assertEquals(4, rows(transposed));
        assertEquals(1, cols(transposed));
        assertTrue(equalsMatrix(expected, transposed));
    }


    @Test
    public void testMultiplyMatrixTuple() {
        double[][] a = matrix(4, 4);
        double[] b = tuple(1, 2, 3, 1);

        setRow(a, 0, new double[] { 1, 2, 3, 4});
        setRow(a, 1, new double[] { 2, 4, 4, 2});
        setRow(a, 2, new double[] { 8, 6, 4, 1});
        setRow(a, 3, new double[] { 0, 0, 0, 1});

        double[] c= multiply(a, b);
        assertEquals(4, c.length);

        assertEquals(18, c[0], Tuple.epsilon);
        assertEquals(24, c[1], Tuple.epsilon);
        assertEquals(33, c[2], Tuple.epsilon);
        assertEquals(1, c[3], Tuple.epsilon);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalMatrixMultiplication() {
        double[][] a = matrix(5, 7);
        double[][] b = matrix(4, 3);
        multiply(a, b);
    }

    @Test
    public void testIdentityMatrix() {
        double[][] a = squareMatrix(4);
        double[][] b = identity(4);
        setRow(a, 0, new double[] { 0, 1, 2, 4});
        setRow(a, 1, new double[] { 1, 2, 4, 8});
        setRow(a, 2, new double[] { 2, 4, 6, 8});
        setRow(a, 3, new double[] { 4, 8, 16, 32});

        double[][] c = multiply(a, b);
        assertTrue(equalsMatrix(a, c));
    }

    @Test
    public void testDeterminant2x2() {
        double[][] a = squareMatrix(2);
        setRow(a, 0, new double[] { 1, 5});
        setRow(a, 1, new double[] { -3, 2});
        assertEquals(17, determinant(a), Tuple.epsilon);
    }

    @Test
    public void testSubMatrix3x3() {
        double[][] a = squareMatrix(3);
        double[][] expected = squareMatrix(2);

        setRow(a, 0, new double[] {1, 5, 0});
        setRow(a, 1, new double[] {-3, 2, 7});
        setRow(a, 2, new double[] {0, 6, 3});

        setRow(expected, 0, new double[] { -3, 2});
        setRow(expected, 1, new double[] { 0, 6});

        assertTrue(equalsMatrix(expected, subMatrix(a, 0, 2)));
    }

    @Test
    public void testSubMatrix4x4() {
        double[][] a = squareMatrix(4);
        double[][] expected = squareMatrix(3);

        setRow(a, 0, new double[] { -6, 1, 1, 6});
        setRow(a, 1, new double[] { -8, 5, 8, 6});
        setRow(a, 2, new double[] { -1, 0, 8, 2});
        setRow(a, 3, new double[] { -7, 1, -1, 1});

        setRow(expected, 0, new double[] { -6, 1, 6});
        setRow(expected, 1, new double[] { -8, 8, 6});
        setRow(expected, 2, new double[] {-7, -1, 1});

        assertTrue(equalsMatrix(expected, subMatrix(a, 2, 1)));
    }

    @Test
    public void testMinor() {
        double[][] a = squareMatrix(3);

        setRow(a, 0, new double[] { 3, 5, 0});
        setRow(a, 1, new double[] { 2, -1, -7});
        setRow(a, 2, new double[] { 6, -1, 5});

        assertEquals(25, minor(a,1 , 0), Tuple.epsilon);
    }

    @Test
    public void testCofactor() {
        double[][] a = squareMatrix(3);

        setRow(a, 0, new double[] { 3, 5, 0});
        setRow(a, 1, new double[] { 2, -1, -7});
        setRow(a, 2, new double[] { 6, -1, 5});

        assertEquals(-12, cofactor(a,0 , 0), Tuple.epsilon);
        assertEquals(-25, cofactor(a,1 , 0), Tuple.epsilon);

    }

    @Test
    public void testDeterminant3x3() {
        double[][] a = squareMatrix(3);

        setRow(a, 0, new double[] { 1, 2, 6});
        setRow(a, 1, new double[] { -5, 8, -4});
        setRow(a, 2, new double[] { 2, 6, 4});

        assertEquals(56, cofactor(a, 0, 0), Tuple.epsilon);
        assertEquals(12, cofactor(a, 0, 1), Tuple.epsilon);
        assertEquals(-46, cofactor(a, 0, 2), Tuple.epsilon);
        assertEquals(-196, determinant(a), Tuple.epsilon);

    }

    @Test
    public void testDeterminant4x4() {
        double[][] a = squareMatrix(4);

        setRow(a, 0, new double[] { -2, -8, 3, 5});
        setRow(a, 1, new double[] { -3, 1, 7, 3});
        setRow(a, 2, new double[] { 1, 2, -9, 6});
        setRow(a, 3, new double[] { -6, 7, 7, -9});

        assertEquals(690, cofactor(a, 0, 0), Tuple.epsilon);
        assertEquals(447, cofactor(a, 0, 1), Tuple.epsilon);
        assertEquals(210, cofactor(a, 0, 2), Tuple.epsilon);
        assertEquals(51, cofactor(a, 0, 3), Tuple.epsilon);
        assertEquals(-4071, determinant(a), Tuple.epsilon);
    }

    @Test
    public void testIsInvertible() {
        double[][] a = squareMatrix(4);
        double[][] b = squareMatrix(4);

        setRow(a, 0, new double[] { 6, 4, 4, 4 });
        setRow(a, 1, new double[] { 5, 5, 7, 6 });
        setRow(a, 2, new double[] { 4, -9, 3, 7 });
        setRow(a, 3, new double[] { 9, 1, 7, 6 });

        setRow(b, 0, new double[] { -4, 2, -2, -3 });
        setRow(b, 1, new double[] { 9, 6, 2, 6 });
        setRow(b, 2, new double[] { 0, -5, 1, -5 });
        setRow(b, 3, new double[] { 0, 0, 0, 0 });

        assertTrue(isInvertible(a));
        assertFalse(isInvertible(b));
    }

    @Test
    public void testInverse() {
        double[][] a = squareMatrix(4);
        double[][] expectedInverse = squareMatrix(4);

        setRow(a, 0, new double[] { -5, 2, 6, -8});
        setRow(a, 1, new double[] { 1, -5, 1, 8});
        setRow(a, 2, new double[] { 7, 7, -6, -7});
        setRow(a, 3, new double[] { 1, -3, 7, 4});

        setRow(expectedInverse, 0, new double[] { 0.21805, 0.45113, 0.24060, -0.04511});
        setRow(expectedInverse, 1, new double[] { -0.80827, -1.45677, -0.44361, 0.52068});
        setRow(expectedInverse, 2, new double[] { -0.07895, -0.22368, -0.05263, 0.19737});
        setRow(expectedInverse, 3, new double[] { -0.52256, -0.81391, -0.30075, 0.30639});

        double[][] b = inverse(a);
        assertEquals(532, determinant(a), Tuple.epsilon);
        assertEquals(-160, cofactor(a, 2, 3), Tuple.epsilon);
        assertEquals(-160.0 / 532.0, b[3][2], Tuple.epsilon);
        assertEquals(105, cofactor(a, 3, 2), Tuple.epsilon);
        assertEquals(105.0 / 532.0, b[2][3], Tuple.epsilon);
        for (int row = 0; row < rows(expectedInverse); row++) {
            for (int col = 0; col < cols(expectedInverse); col++) {
                assertEquals(expectedInverse[row][col], b[row][col], Tuple.epsilon);
            }
        }
        assertTrue(equalsMatrix(expectedInverse, b));
    }

    @Test
    public void testInverse2() {
        double[][] a = squareMatrix(4);
        double[][] expectedInverseA = squareMatrix(4);

        double[][] b = squareMatrix(4);
        double[][] expectedInverseB = squareMatrix(4);

        setRow(a, 0, new double[] { 8, -5, 9, 2 });
        setRow(a, 1, new double[] { 7, 5, 6, 1 });
        setRow(a, 2, new double[] { -6, 0, 9, 6 });
        setRow(a, 3, new double[] { -3, 0, -9, -4 });

        setRow(expectedInverseA, 0, new double[] { -0.15385, -0.15385, -0.28205, -0.53846 });
        setRow(expectedInverseA, 1, new double[] { -0.07692, 0.12308, 0.02564, 0.03077 });
        setRow(expectedInverseA, 2, new double[] { 0.35897, 0.35897, 0.43590, 0.92308 });
        setRow(expectedInverseA, 3, new double[] { -0.69231, -0.69231, -0.76923, -1.92308 });

        double[][] inverseA = inverse(a);
        for (int row = 0; row < rows(expectedInverseA); row++) {
            for (int col = 0; col < cols(expectedInverseA); col++) {
                assertEquals(expectedInverseA[row][col], inverseA[row][col], Tuple.epsilon);
            }
        }

        setRow(b, 0, new double[] { 9, 3, 0, 9 });
        setRow(b, 1, new double[] { -5, -2, -6, -3 });
        setRow(b, 2, new double[] { -4, 9, 6, 4 });
        setRow(b, 3, new double[] { -7, 6, 6, 2 });

        setRow(expectedInverseB, 0, new double[] { -0.04074, -0.07778, 0.14444, -0.22222 });
        setRow(expectedInverseB, 1, new double[] { -0.07778, 0.03333, 0.36667, -0.33333 });
        setRow(expectedInverseB, 2, new double[] { -0.02901, -0.14630, -0.10926, 0.12963 });
        setRow(expectedInverseB, 3, new double[] { 0.17778, 0.06667, -0.26667, 0.33333 });

        double[][] inverseB = inverse(b);

        for (int row = 0; row < rows(expectedInverseB); row++) {
            for (int col = 0; col < cols(expectedInverseB); col++) {
                assertEquals(expectedInverseB[row][col], inverseB[row][col], Tuple.epsilon);
            }
        }
    }

    @Test
    public void testInverse3() {
        double[][] a = squareMatrix(4);
        double[][] b = squareMatrix(4);

        setRow(a, 0, new double[] { 3, -9, 7, 3});
        setRow(a, 1, new double[] { 3, -8, 2, -9});
        setRow(a, 2, new double[] { -4, 4, 4, 1});
        setRow(a, 3, new double[] { 6, 5, -1, 1});

        setRow(b, 0, new double[] { 8, 2, 2, 2});
        setRow(b, 1, new double[] { 3, -1, 7, 0});
        setRow(b, 2, new double[] { 7, 0, 5, 4});
        setRow(b, 3, new double[] { 6, -2, 0, 5});

        double[][] c = multiply(a, b);
        double[][] expected = multiply(c, inverse(b));
        for (int row = 0; row < rows(expected); row++) {
            for (int col = 0; col < cols(expected); col++) {
                assertEquals(expected[row][col], a[row][col], Tuple.epsilon);
            }
        }
    }
}
