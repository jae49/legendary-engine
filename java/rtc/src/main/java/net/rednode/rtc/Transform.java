package net.rednode.rtc;

public class Transform {

    public static double[][] translation(double x, double y, double z) {
        double[][] translated = Matrix.identity(4);
        translated[0][3] = x;
        translated[1][3] = y;
        translated[2][3] = z;


        return translated;
    }
}
