package net.rednode.rtc.test;

import net.rednode.rtc.Matrix;
import net.rednode.rtc.Tuple;
import org.junit.Test;

import static net.rednode.rtc.Transform.scale;
import static net.rednode.rtc.Transform.translate;
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

        double[] result = Matrix.multiply(Matrix.inverse(transform), point);

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
        double[] result = Matrix.multiply(Matrix.inverse(transform), vector);

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

}
