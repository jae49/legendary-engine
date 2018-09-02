package net.rednode.rtc;

import net.rednode.rtc.model.TupleType;

@SuppressWarnings("WeakerAccess")
public class Tuple {

    public static final int X = 0;
    public static final int Y = 1;
    public static final int Z = 2;
    public static final int W = 3;
    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int BLUE = 2;
    public static final int ALPHA = 3;
    public static final double epsilon = 0.0000001;


    public static double[] tuple(double x, double y, double z, double w) {
        return new double[] {x, y, z, w};
    }

    public static double[] point(double x, double y, double z) {
        return new double[] {x, y, z, 1.0};
    }

    public static double[] vector(double x, double y, double z) {
        return new double[] {x, y, z, 0.0};
    }

    public static double[] color(double r, double g, double b) {
        return new double[] {r, g, b, 0};
    }

    public static double[] color(double red, double green, double blue, double alpha) {
        return new double[] {red, green, blue, alpha};
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
        //it may be redundant, but its clearer
        //noinspection RedundantIfStatement
        if (Math.abs(W(a) - W(b)) > epsilon) return false;
        return true;
    }

    public static double[] add(double[] a, double[] b) {
        return new double[] {a[X] + b[X],
                             a[Y] + b[Y],
                             a[Z] + b[Z],
                             a[W] + b[W]};
    }

    public static double[] sub(double[] a, double[] b) {
        return new double[] {a[X] - b[X],
                             a[Y] - b[Y],
                             a[Z] - b[Z],
                             a[W] - b[W]};
    }

    public static double[] negate(double[] tuple) {
        return new double[] {0.0 - tuple[X],
                             0.0 - tuple[Y],
                             0.0 - tuple[Z],
                             0.0 - tuple[W]};
    }

    public static double[] multiply(double[] tuple, double scalar) {
        return new double[] {tuple[X] * scalar,
                             tuple[Y] * scalar,
                             tuple[Z] * scalar,
                             tuple[W] * scalar};
    }

    public static double[] multiply(double[] a, double[] b) {
        return new double[] {a[RED] * b[RED],
                             a[GREEN] * b[GREEN],
                             a[BLUE] * b[BLUE],
                             a[ALPHA] * b[ALPHA]};
    }

    public static double[] divide(double[] tuple, double scalar) {
        return new double[] {tuple[X] / scalar,
                             tuple[Y] / scalar,
                             tuple[Z] / scalar,
                             tuple[W] / scalar};
    }

    public static double magnitude(double[] tuple) {
        return Math.sqrt(tuple[X] * tuple[X] +
                         tuple[Y] * tuple[Y] +
                         tuple[Z] * tuple[Z] +
                         tuple[W] * tuple[W]);
    }

    public static double[] normalize(double[] tuple) {
        double m = magnitude(tuple);
        return new double[] {tuple[X] / m,
                             tuple[Y] / m,
                             tuple[Z] / m,
                             tuple[W] / m};
    }

    public static double dot(double[] a, double[] b) {
        return a[X] * b[X] +
                a[Y] * b[Y] +
                a[Z] * b[Z] +
                a[W] * b[W];
    }

    public static double[] cross(double[] vectorA, double[] vectorB) {
        return vector(vectorA[Y] * vectorB[Z] - vectorA[Z] * vectorB[Y],
                      vectorA[Z] * vectorB[X] - vectorA[X] * vectorB[Z],
                      vectorA[X] * vectorB[Y] - vectorA[Y] * vectorB[X]);
    }

}
