package net.rednode.rtc;

import java.io.OutputStreamWriter;

public class Canvas {

    public static double[][][] create(int width, int height) {
        return new double[width][height][4];
    }

    public static void writePixel(double[][][] canvas, int x, int y, double[] color) {
        System.arraycopy(color, 0, canvas[x][y], 0, 4);
    }

    public static double[] pixelAt(double[][][] canvas, int x, int y) {
        return canvas[x][y];
    }

    public static int scale256(double d) {
        if (d < 0.0) return 0;
        if (d > 1.0) return 255;
        return (int) Math.round(d * 255.0);
    }

    public static int getWidth(double[][][] canvas) {
        return canvas.length;
    }

    public static int getHeight(double[][][] canvas) {
        return canvas[0].length;
    }

    public static String generatePPMHeader(double[][][] canvas) {
        int width = getWidth(canvas);
        int height = getHeight(canvas);
        return "P3\n" + width + " " + height + "\n255\n";
    }

    public static void writeCanvasPPMData(double[][][] canvas, OutputStreamWriter os) {
        int width = getWidth(canvas);
        int height = getHeight(canvas);
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                //TODO: Finish
            }
        }
    }

}
