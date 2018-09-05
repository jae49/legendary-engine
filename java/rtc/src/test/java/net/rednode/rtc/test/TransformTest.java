package net.rednode.rtc.test;

import net.rednode.rtc.Matrix;
import net.rednode.rtc.Tuple;
import org.junit.Test;

import static net.rednode.rtc.Transform.translation;
import static net.rednode.rtc.Tuple.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TransformTest {

    @Test
    public void testTranslationMultiplication() {
        double[] point = point(-3, 4, 5);
        double[][] transform = translation(5, -3, 2);

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
        double[][] transform = translation(5, -3, 2);

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
        double[][] transform = translation(5, -3, 2);
        double[] vector = vector(-3, 4, 5);
        assertTrue(Tuple.tuplesEqual(vector, Matrix.multiply(transform, vector)));
    }
}
