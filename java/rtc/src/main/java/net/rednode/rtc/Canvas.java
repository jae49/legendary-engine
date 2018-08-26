package net.rednode.rtc;

public class Canvas {

    public static double[][][] create(int width, int height) {
        return new double[width][height][4];
    }

    public static void writePixel(double[][][] canvas, int x, int y, double[] color) {
        System.arraycopy(color, 0, canvas[x][y], 0, 4);
    }

}
