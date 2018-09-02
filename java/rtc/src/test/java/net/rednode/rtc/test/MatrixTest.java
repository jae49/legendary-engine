package net.rednode.rtc.test;

import net.rednode.rtc.Tuple;
import org.junit.Test;

import static net.rednode.rtc.Matrix.*;
import static org.junit.Assert.assertEquals;

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
}
