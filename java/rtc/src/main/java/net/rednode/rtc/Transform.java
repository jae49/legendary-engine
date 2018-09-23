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

    public static double[][] rotateX(double radians) {
        double[][] rotation_x = Matrix.identity(4);
        rotation_x[1][1] = Math.cos(radians);
        rotation_x[1][2] = -Math.sin(radians);
        rotation_x[2][1] = Math.sin(radians);
        rotation_x[2][2] = Math.cos(radians);
        return rotation_x;
    }

    public static double[][] rotateY(double radians) {
        double[][] rotation_y = Matrix.identity(4);
        rotation_y[0][0] = Math.cos(radians);
        rotation_y[0][2] = Math.sin(radians);
        rotation_y[2][0] = -Math.sin(radians);
        rotation_y[2][2] = Math.cos(radians);
        return rotation_y;
    }

    public static double[][] rotateZ(double radians) {
        double[][] rotation_z = Matrix.identity(4);
        rotation_z[0][0] = Math.cos(radians);
        rotation_z[0][1] = -Math.sin(radians);
        rotation_z[1][0] = Math.sin(radians);
        rotation_z[1][1] = Math.cos(radians);
        return rotation_z;
    }
}
