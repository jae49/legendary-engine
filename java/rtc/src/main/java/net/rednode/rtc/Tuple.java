package net.rednode.rtc;

import net.rednode.rtc.model.TupleType;

public class Tuple {

    private static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 2;
    private static final int W = 3;
    public static final double epsilon = 0.0000001;


    public static double[] point(double x, double y, double z) {
        return new double[] {x, y, z, 1.0};
    }

    public static double[] vector(double x, double y, double z) {
        return new double[] {x, y, z, 0.0};
    }

    public static double X(double[] tuple) {
        return tuple[X];
    }

    public static double Y(double[] tuple) {
        return tuple[Y];
    }

    public static double Z(double[] tuple) {
        return tuple[Z];
    }

    public static double W(double[] tuple) {
        return tuple[W];
    }

    public static boolean isPoint(double[] tuple) {
        return Math.abs(tuple[3] - TupleType.Point.typenum) < epsilon;
    }

    public static boolean isVector(double[] tuple) {
        return Math.abs(tuple[3] - TupleType.Vector.typenum) < epsilon;
    }

    public static boolean tuplesEqual(double[] a, double[] b) {
        if (Math.abs(X(a) - X(b)) > epsilon) return false;
        if (Math.abs(Y(a) - Y(b)) > epsilon) return false;
        if (Math.abs(Z(a) - Z(b)) > epsilon) return false;
        if (Math.abs(W(a) - W(b)) > epsilon) return false;
        return true;
    }

}
