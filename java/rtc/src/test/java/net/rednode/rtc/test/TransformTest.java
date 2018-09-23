package net.rednode.rtc.test;

import net.rednode.rtc.Matrix;
import net.rednode.rtc.Tuple;
import org.junit.Test;

import static net.rednode.rtc.Matrix.inverse;
import static net.rednode.rtc.Transform.*;
import static net.rednode.rtc.Tuple.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransformTest {

    @Test
    public void testTranslationMultiplication() {
        double[] point = point(-3, 4, 5);
        double[][] transform = translate(5, -3, 2);

        double[] expected = point(2, 1, 7);
        double[] result = Matrix.multiply(transform, point);
        assertTrue(Tuple.tuplesEqual(expected, result));
        assertEquals(2, X(result), Tuple.epsilon);
        assertEquals(1, Y(result), Tuple.epsilon);
        assertEquals(7, Z(result), Tuple.epsilon);
    }

    @Test
    public void testTranslationInverseMultiplication() {
        double[] point = point(-3, 4, 5);
        double[][] transform = translate(5, -3, 2);

        double[] expected = point(-8, 7, 3);

        double[] result = Matrix.multiply(inverse(transform), point);

        assertEquals(4, result.length);
        assertTrue(Tuple.tuplesEqual(expected, result));
        assertEquals(-8, X(expected), Tuple.epsilon);
        assertEquals(7, Y(expected), Tuple.epsilon);
        assertEquals(3, Z(expected), Tuple.epsilon);
        assertEquals(-8, X(result), Tuple.epsilon);
        assertEquals(7, Y(result), Tuple.epsilon);
        assertEquals(3, Z(result), Tuple.epsilon);
    }

    @Test
    public void testTranslationIgnoresVectors() {
        double[][] transform = translate(5, -3, 2);
        double[] vector = vector(-3, 4, 5);
        assertTrue(Tuple.tuplesEqual(vector, Matrix.multiply(transform, vector)));
    }

    @Test
    public void testScaledPoint() {
        double[][] transform = scale(2, 3, 4);
        double[] point = point(-4, 6, 8);

        double[] expected = point(-8, 18, 32);
        double[] result = Matrix.multiply(transform, point);

        assertTrue(Tuple.tuplesEqual(expected, result));
    }

    @Test
    public void testScaledVector() {
        double[][] transform = scale(2, 3, 4);
        double[] vector = vector(-4, 6, 8);

        double[] expected = vector(-8, 18, 32);
        double[] result = Matrix.multiply(transform, vector);

        assertTrue(Tuple.tuplesEqual(expected, result));
    }

    @Test
    public void testScaledInverseVector() {
        double[][] transform = scale(2, 3, 4);
        double[] vector = vector(-4, 6, 8);

        double[] expected = vector(-2, 2, 2);
        double[] result = Matrix.multiply(inverse(transform), vector);

        assertTrue(Tuple.tuplesEqual(expected, result));
    }

    @Test
    public void testScaledReflection() {
        double[][] transform = scale(-1, 1, 1);
        double[] point = point(2, 3, 4);

        double[] expected = point(-2, 3, 4);
        double[] result = Matrix.multiply(transform, point);

        assertTrue(Tuple.tuplesEqual(expected, result));
    }

    @Test
    public void testRotationX() {
        double[] point = point(0, 1, 0);
        double[][] half_quarter = rotateX(Math.PI / 4.0);
        double[][] full_quarter = rotateX(Math.PI / 2.0);

        //half quarter * point
        double[] expected = point(0, Math.sqrt(2.0) / 2.0, Math.sqrt(2.0) / 2.0);
        double[] result = Matrix.multiply(half_quarter, point);
        assertEquals(expected[X], result[X], Tuple.epsilon);
        assertEquals(expected[Y], result[Y], Tuple.epsilon);
        assertEquals(expected[Z], result[Z], Tuple.epsilon);

        //full_quarter * point
        expected = point(0, 0, 1);
        result = Matrix.multiply(full_quarter, point);
        assertEquals(expected[X], result[X], Tuple.epsilon);
        assertEquals(expected[Y], result[Y], Tuple.epsilon);
        assertEquals(expected[Z], result[Z], Tuple.epsilon);
    }

    @Test
    public void testRotationXInverse() {
        double[] point = point(0, 1, 0);
        double[][] half_quarter = rotateX(Math.PI / 4.0);
        double[][] inv_half_quarter = inverse(half_quarter);

        double[] expected = point(0, Math.sqrt(2) / 2.0, -(Math.sqrt(2) / 2.0));
        double[] result = Matrix.multiply(inv_half_quarter, point);
        assertEquals(expected[X], result[X], Tuple.epsilon);
        assertEquals(expected[Y], result[Y], Tuple.epsilon);
        assertEquals(expected[Z], result[Z], Tuple.epsilon);

    }

    @Test
    public void testRotationY() {
        double[] point = point(0, 0, 1);
        double[][] half_quarter = rotateY(Math.PI / 4.0);
        double[][] full_quarter = rotateY(Math.PI / 2.0);

        //half quarter * point
        double[] expected = point(Math.sqrt(2.0) / 2.0, 0, Math.sqrt(2.0) / 2.0);
        double[] result = Matrix.multiply(half_quarter, point);
        assertEquals(expected[X], result[X], Tuple.epsilon);
        assertEquals(expected[Y], result[Y], Tuple.epsilon);
        assertEquals(expected[Z], result[Z], Tuple.epsilon);

        //full_quarter * point
        expected = point(1, 0, 0);
        result = Matrix.multiply(full_quarter, point);
        assertEquals(expected[X], result[X], Tuple.epsilon);
        assertEquals(expected[Y], result[Y], Tuple.epsilon);
        assertEquals(expected[Z], result[Z], Tuple.epsilon);
    }

    @Test
    public void testRotationZ() {
        double[] point = point(0, 1, 0);
        double[][] half_quarter = rotateZ(Math.PI / 4.0);
        double[][] full_quarter = rotateZ(Math.PI / 2.0);

        //half quarter * point
        double[] expected = point(-(Math.sqrt(2.0) / 2.0),Math.sqrt(2.0) / 2.0, 0);
        double[] result = Matrix.multiply(half_quarter, point);
        assertEquals(expected[X], result[X], Tuple.epsilon);
        assertEquals(expected[Y], result[Y], Tuple.epsilon);
        assertEquals(expected[Z], result[Z], Tuple.epsilon);

        //full_quarter * point
        expected = point(-1, 0, 0);
        result = Matrix.multiply(full_quarter, point);
        assertEquals(expected[X], result[X], Tuple.epsilon);
        assertEquals(expected[Y], result[Y], Tuple.epsilon);
        assertEquals(expected[Z], result[Z], Tuple.epsilon);
    }

}
