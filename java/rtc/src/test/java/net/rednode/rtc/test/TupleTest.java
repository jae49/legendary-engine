package net.rednode.rtc.test;

import static net.rednode.rtc.Tuple.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
                TupleType.determine(new double[] { 4.3, -4.2, 3.1, 1.0}));
        assertEquals(TupleType.Vector,
                TupleType.determine(new double[] { 4.3, -4.2, 3.1, 0.0}));
    }

    // test a tuple with a w = -1 to see if properly
    // throws an InvalidArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTupleType() {
        TupleType.determine(new double[] {4.3, -4.2, 3.1, -5.0});
    }

    @Test
    public void testTupleEquality() {
        assertTrue(tuplesEqual(new double[] {4.3, -4.2, 3.1, 1.0},
                        new double[] {4.3, -4.2, 3.1, 1.0}));
        assertFalse(tuplesEqual(new double[] {4.3, -4.1, 3.1, 1.0},
                        new double[] {4.3, -4.2, 3.1, 1.0}));
        assertFalse(tuplesEqual(new double[] {4.3, -4.2, 3.1, 0.0},
                        new double[] {4.3, -4.2, 3.1, 1.0}));
        assertFalse(tuplesEqual(new double[] {4.3, -4.2, 3.1, 1.0},
                        new double[] {4.3, 4.2, 3.1, 1.0}));
        assertFalse(tuplesEqual(new double[] {-4.3, -4.2, 3.1, 1.0},
                        new double[] {4.3, -4.2, 3.1, 1.0}));
        assertFalse(tuplesEqual(new double[] {4.3, -4.2, -3.1, 1.0},
                        new double[] {4.3, -4.2, 3.1, 1.0}));


    }


}
