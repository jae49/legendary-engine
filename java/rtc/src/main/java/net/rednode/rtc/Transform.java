package net.rednode.rtc;

public class Transform {

    public static double[][] translate(double x, double y, double z) {
        double[][] translated = Matrix.identity(4);
        translated[0][3] = x;
        translated[1][3] = y;
        translated[2][3] = z;


        return translated;
    }

    public static double[][] scale(double x, double y, double z) {
        double[][] scaled = Matrix.identity(4);
        scaled[0][0] = x;
        scaled[1][1] = y;
        scaled[2][2] = z;
        return scaled;
    }
}
