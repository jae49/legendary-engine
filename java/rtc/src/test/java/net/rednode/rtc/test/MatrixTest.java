package net.rednode.rtc.test;

import net.rednode.rtc.Tuple;
import org.junit.Test;

import static net.rednode.rtc.Matrix.squareMatrix;
import static net.rednode.rtc.Matrix.size;
import static org.junit.Assert.assertEquals;

public class MatrixTest {

    @Test
    public void test4x4MatrixCreation() {
        double[][] m = squareMatrix(4);
        m[0] = new double[] {1, 2, 3, 4};
        m[1] = new double[] {5.5, 6.5, 7.5, 8.5};
        m[2] = new double[] {9, 10, 11, 12};
        m[3] = new double[] {13.5, 14.5, 15.5, 16.5};
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
        m[0] = new double[] {-3, 5};
        m[1] = new double[] {1, -2};
        assertEquals(2, size(m));
    }

    @Test
    public void test3x3Size() {
        double[][] m = squareMatrix(3);
        m[0] = new double[] {-3, 5, 0};
        m[1] = new double[] {1, -2, -7};
        m[2] = new double[] {0, 1, 1};
        assertEquals(3, size(m));
    }
}
