package net.rednode.rtc.test;

import static net.rednode.rtc.Tuple.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import net.rednode.rtc.Canvas;
import net.rednode.rtc.model.TupleType;

import org.junit.Test;


public class TupleTest {


    @Test
    public void testTupleTypes() {
        double[] a;
        // test a tuple with random values and w = 1.0
        // to see if it comes back as a point
        a = point(4.3, -4.2, 3.1);
        assertEquals(4.3, X(a), epsilon);
        assertEquals(-4.2, Y(a), epsilon);
        assertEquals(3.1, Z(a), epsilon);
        assertEquals(1.0, W(a), epsilon);
        assertTrue(isPoint(a));
        assertFalse(isVector(a));


        // test a tuple with random values and w = 0.0
        // to see if it comes back as a vector
        a = vector(4.3, -4.2, 3.1);
        assertEquals(4.3, X(a), epsilon);
        assertEquals(-4.2, Y(a), epsilon);
        assertEquals(3.1, Z(a), epsilon);
        assertEquals(0.0, W(a), epsilon);
        assertFalse(isPoint(a));
        assertTrue(isVector(a));

    }

    @Test
    public void testValidTupleTypes() {
        assertEquals(TupleType.Point,
                TupleType.determine(tuple(4.3, -4.2, 3.1, 1.0)));
        assertEquals(TupleType.Vector,
                TupleType.determine(tuple(4.3, -4.2, 3.1, 0.0)));
    }

    // test a tuple with a w = -1 to see if properly
    // throws an InvalidArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTupleType() {
        TupleType.determine(new double[] {4.3, -4.2, 3.1, -5.0});
    }

    @Test
    public void testTupleEquality() {
        assertTrue(tuplesEqual(tuple(4.3, -4.2, 3.1, 1.0),
                               tuple(4.3, -4.2, 3.1, 1.0)));
        assertFalse(tuplesEqual(tuple(4.3, -4.1, 3.1, 1.0),
                                tuple(4.3, -4.2, 3.1, 1.0)));
        assertFalse(tuplesEqual(tuple(4.3, -4.2, 3.1, 0.0),
                                tuple(4.3, -4.2, 3.1, 1.0)));
        assertFalse(tuplesEqual(tuple(4.3, -4.2, 3.1, 1.0),
                                tuple(4.3, 4.2, 3.1, 1.0)));
        assertFalse(tuplesEqual(tuple(-4.3, -4.2, 3.1, 1.0),
                                tuple(4.3, -4.2, 3.1, 1.0)));
        assertFalse(tuplesEqual(tuple(4.3, -4.2, -3.1, 1.0),
                                tuple(4.3, -4.2, 3.1, 1.0)));


    }

    @Test
    public void testValidTupleAddition() {
        double[] a, b, c;

        // Test general tuple addition
        a = point(3, -2, 5);
        b = vector(-2, 3, 1);
        c = point(1, 1, 6); // expected value
        assertTrue(tuplesEqual(add(a, b), c));

        // Test that adding a vector to a vector gives
        // the expected sums as well as resulting in a vector
        a = vector( 3, 4, 5);
        b = vector( 6, 7, 8);
        c = vector( 9, 11, 13); // expected value
        assertTrue(tuplesEqual(add(a, b), c));
        assertTrue(isVector(add(a, b)));

        // Test that adding a vector to a point gives
        // a point
        a = point( 1.1, 2.2, -3.3);
        b = vector(-4.1, 5.2, -6.3);
        c = point( -3.0, 7.4, -9.6);
        assertTrue(tuplesEqual(add(a, b), c));
        assertTrue(isPoint(add(a, b)));

        // Test that adding a point to a vector gives
        // a point
        a = vector( 3.9, 4.4, -16.2);
        b = point( 3.7, -5.6, 2.1);
        c = point( 7.6, -1.2, -14.1);
        assertTrue(tuplesEqual(add(a, b), c));
        assertTrue(isPoint(add(a, b)));


    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTupleAddition() {
        double[] a, b;
        // Test that adding a point to a point ends up with an illogical
        // result (throws an invalid argument exception when trying to
        // get its type)
        a = point(1, 1,1);
        b = point(2, 2, 2);
        TupleType.determine(add(a, b));
    }

    @Test
    public void testValidTupleSubtraction() {
        double[] a, b, c;

        // Check that subtracting ends up with the
        // correct value and that the resulting tuple
        // is a vector
        a = point(3, 2, 1);
        b = point(5, 6, 7);
        c = vector(-2, -4, -6);
        assertTrue(tuplesEqual(sub(a, b), c));
        assertTrue(isVector(sub(a, b)));

        // Check that subtracting a vector from a
        // point results in a point
        a = point(3,2, 1);
        b = vector(5, 6, 7);
        c = point(-2, -4, -6);
        assertTrue(tuplesEqual(sub(a, b), c));
        assertTrue(isPoint(sub(a, b)));

        // Check that subtracting a vector from a
        // vector results in a vector
        a = vector(3, 2, 1);
        b = vector(5, 6, 7);
        c = vector(-2, -4, -6);
        assertTrue(tuplesEqual(sub(a, b), c));
        assertTrue(isVector(sub(a, b)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTupleSubtraction() {
        double[] a, b;

        // Check that subtracting a point from a vector
        // results in a nonsense result
        a = vector(0, 0, 0);
        b = point(1, 2, 3);
        TupleType.determine(sub(a, b));
    }

    @Test
    public void testNegativeSubtraction() {
        double[] a, b, c;

        a = vector(0, 0, 0);
        b = vector(1, -2, 3);
        c = vector(-1, 2, -3); //expected result
        assertTrue(tuplesEqual(sub(a, b), c));
        assertTrue(isVector(sub(a,b)));
    }

    @Test
    public void testNegateTuple() {
        double[] a, c;
        a = tuple(1, -2, 3, -4);
        c = tuple(-1, 2, -3, 4); //expected result
        assertTrue(tuplesEqual(negate(a), c));
    }

    @Test
    public void testMultiplyTuples() {
        double[] a, c;
        double scalar;

        //Multiply a tuple by a scalar
        a = tuple(1, -2, 3, -4);
        scalar = 3.5;
        c = tuple(3.5, -7, 10.5, -14); //expected result
        assertTrue(tuplesEqual(multiply(a, scalar), c));

        //Multiply a tuple by a fraction
        a = tuple(1, -2, 3, -4);
        scalar = 0.5;
        c = tuple(0.5, -1, 1.5, -2); //expected result
        assertTrue(tuplesEqual(multiply(a, scalar), c));
    }

    @Test
    public void testDivideTuples() {
        double[] a, c;
        double scalar;

        //Divide a tuple by a scalar
        a = tuple(1, -2, 3, -4);
        scalar = 2;
        c = tuple(0.5, -1, 1.5, -2);
        assertTrue(tuplesEqual(divide(a, scalar), c));
    }

    @Test
    public void testMagnitudeVectors() {
        double[] a;

        a = vector(1, 0, 0);
        assertEquals(1.0, magnitude(a), epsilon);

        a = vector(0, 1, 0);
        assertEquals(1.0, magnitude(a), epsilon);

        a = vector(0, 0, 1);
        assertEquals(1.0, magnitude(a), epsilon);

        a = vector(1, 2, 3);
        assertEquals(Math.sqrt(14), magnitude(a), epsilon);

        a = vector(-1, -2, -3);
        assertEquals(Math.sqrt(14), magnitude(a), epsilon);
    }

    @Test
    public void testNormalizeVectors() {
        double[] a, c;
        a = vector(4, 0, 0);
        c = vector(1, 0, 0);
        assertTrue(tuplesEqual(normalize(a), c));

        a = vector(1, 2, 3);
        c = vector(1.0 / Math.sqrt(14), 2.0 / Math.sqrt(14), 3.0 / Math.sqrt(14));
        assertTrue(tuplesEqual(normalize(a), c));
    }

    @Test
    public void testMagnitudeNormalizedVector() {
        double[] a;
        a = vector(1, 2, 3);
        assertEquals(1.0, magnitude(normalize(a)), epsilon);
    }

    @Test
    public void testDotProduct() {
        double[] a, b;

        a = vector(1, 2, 3);
        b = vector(2, 3, 4);
        assertEquals(20.0, dot(a, b), epsilon);
    }

    @Test
    public void testCrossProduct() {
        double[] a, b, c;

        a = vector(1, 2, 3);
        b = vector(2, 3, 4);
        c = vector(-1, 2, -1); //expected result
        assertTrue(tuplesEqual(cross(a, b), c));

        a = vector(1, 2, 3);
        b = vector(2, 3, 4);
        c = vector(1, -2, 1); //expected result
        assertTrue(tuplesEqual(cross(b, a), c));
    }

    @Test
    public void testColorCreation() {
        double[] c;

        c = color(-0.5, 0.4, 1.7);
        assertEquals(-0.5, c[RED], epsilon);
        assertEquals(0.4, c[GREEN], epsilon);
        assertEquals(1.7, c[BLUE], epsilon);
        assertEquals(0.0, c[ALPHA], epsilon);

        c = color(0.4, -4.1, -0.7, 0.3);
        assertEquals(0.4, c[RED], epsilon);
        assertEquals(-4.1, c[GREEN], epsilon);
        assertEquals(-0.7, c[BLUE], epsilon);
        assertEquals(0.3, c[ALPHA], epsilon);
    }

    @Test
    public void testColorAdd() {
        double[] a, b, c;
        a = color(0.9, 0.6, 0.75);
        b = color(0.7, 0.1, 0.25);
        c = color(1.6, 0.7, 1.0); // expected value
        assertTrue(tuplesEqual(add(a,b), c));
    }

    @Test
    public void testColorSubtract() {
        double[] a, b, c;
        a = color(0.9, 0.6, 0.75);
        b = color(0.7, 0.1, 0.25);
        c = color(0.2, 0.5, 0.5);
        assertTrue(tuplesEqual(sub(a,b), c));
    }

    @Test
    public void testColorMultiply() {
        double[] a, b, c;
        double scalar;

        // multiply by scalar
        a = color(0.2, 0.3, 0.4);
        scalar = 2;
        c = color(0.4, 0.6, 0.8); //expected value
        assertTrue(tuplesEqual(multiply(a, scalar), c));

        // multiply one color by another color
        a = color(1, 0.2, 0.4);
        b = color(0.9, 1, 0.1);
        c = color(0.9, 0.2, 0.04); //expected value
        assertTrue(tuplesEqual(multiply(a, b), c));
    }

    @Test
    public void testColorDivide() {
        double[] a, c;
        double scalar;

        a = color(0.5, 0.3, 0.7);
        scalar = 2;
        c = color(0.25, 0.15, 0.35);
        assertTrue(tuplesEqual(divide(a, scalar), c));
    }

    @Test
    public void testCanvasCreate() {
        // create a canvas
        int w = 10;
        int h = 20;
        double[][][] canvas = Canvas.create(w, h);
        // every element should be 0.0
        assertEquals(w, canvas.length);
        for (int width = 0; width < canvas.length; width++) {
            assertEquals(h, canvas[width].length);
            for (int height = 0; height < canvas[width].length; height++) {
                assertEquals(4, canvas[width][height].length);
                for (int i = 0; i < 4; i++) assertEquals(0, canvas[width][height][i], epsilon);
            }
        }
    }

    @Test
    public void testCanvasPixelWrite() {
        // create a canvas
        int w = 10;
        int h = 20;
        double[] red = color(1, 0, 0);
        double[][][] canvas = Canvas.create(w, h);
        Canvas.writePixel(canvas, 2, 3, red);
        assertTrue(tuplesEqual(red, canvas[2][3]));
    }


}
