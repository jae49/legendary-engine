package net.rednode.rtc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;

import static net.rednode.rtc.Tuple.BLUE;
import static net.rednode.rtc.Tuple.GREEN;
import static net.rednode.rtc.Tuple.RED;

public class Canvas {

    public static double[][][] create(int width, int height) {
        return new double[width][height][4];
    }

    public static void writePixel(double[][][] canvas, int x, int y, double[] color) {
        System.arraycopy(color, 0, canvas[x][y], 0, 4);
    }


    @SuppressWarnings("unused")
    public static double[] pixelAt(double[][][] canvas, int x, int y) {
        return canvas[x][y];
    }

    @SuppressWarnings("WeakerAccess")
    public static int scale256(double d) {
        if (d < 0.0) return 0;
        if (d > 1.0) return 255;
        return (int) Math.round(d * 255.0);
    }

    @SuppressWarnings("WeakerAccess")
    public static int getWidth(double[][][] canvas) {
        return canvas.length;
    }

    @SuppressWarnings("WeakerAccess")
    public static int getHeight(double[][][] canvas) {
        return canvas[0].length;
    }

    @SuppressWarnings("WeakerAccess")
    public static void writePPMFile(double[][][] canvas, Path outfile)
    throws IOException {
        try (OutputStreamWriter output =
                     new OutputStreamWriter(new FileOutputStream(outfile.toFile()))) {
            output.write(generatePPMHeader(canvas));
            writeCanvasPPMData(canvas, output);
        }
    }

    public static String generatePPMHeader(double[][][] canvas) {
        int width = getWidth(canvas);
        int height = getHeight(canvas);
        return "P3\n" + width + " " + height + "\n255\n";
    }

    public static void writeCanvasPPMData(double[][][] canvas, OutputStreamWriter os)
    throws IOException {
        int width = getWidth(canvas);
        int height = getHeight(canvas);
        for (int h = 0; h < height; h++) {
            boolean first = true;
            StringBuilder sb = new StringBuilder();
            for (int w = 0; w < width; w++) {
                if (first) {
                    first = false;
                } else {
                    sb.append(' ');
                }
                sb.append(Integer.toString(scale256(canvas[w][h][RED])));
                sb.append(' ');
                sb.append(Integer.toString(scale256(canvas[w][h][GREEN])));
                sb.append(' ');
                sb.append(Integer.toString(scale256(canvas[w][h][BLUE])));
            }
            writeSplitLine(os, sb.toString());
        }
        os.flush();
    }

    private static void writeSplitLine(OutputStreamWriter os, String data)
    throws IOException {
        while (!"".equals(data)) {
            if (data.length() <= 70) {
                os.write(data);
                os.write('\n');
                data = "";
            } else {
                // find first space at 70 or earlier
                int end = data.substring(0, 70).lastIndexOf(' ');
                os.write(data, 0, end);
                os.write('\n');
                data = data.substring(end + 1);
            }
        }
    }

}
