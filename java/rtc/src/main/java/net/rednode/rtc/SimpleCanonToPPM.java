package net.rednode.rtc;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static net.rednode.rtc.Tuple.*;

class SimpleCanonToPPM {

    private static class Projectile {
        double[] position;
        double[] velocity;

        Projectile(double[] position, double[] velocity) {
            this.position = position;
            this.velocity = velocity;
        }
    }

    private static final double[] world_gravity = vector(0, -0.1, 0);
    private static final double[] world_wind = vector(-0.01, 0, 0);
    private static final double[] color_red = color(1, 0, 0);

    private static void tick(Projectile p) {
        p.position = add(p.position, p.velocity);
        p.velocity = add(p.velocity, world_gravity);
        p.velocity = add(p.velocity, world_wind);
    }

    @SuppressWarnings("SameParameterValue")
    private static void writePixelWithinLimit(double[][][] canvas, double[] position, double[] color) {
        int height = Canvas.getHeight(canvas);
        int ix = (int) position[X];
        // invert y for rendering to canvas
        int iy = height - (int) position[Y];
        if (ix < 0) return;
        if (ix > Canvas.getWidth(canvas) -1) return;
        if (iy < 0) return;
        if (iy > Canvas.getHeight(canvas) -1) return;
        Canvas.writePixel(canvas, ix, iy, color);
    }

    private static void printPosition(double[][][] canvas, int count, double[] position) {
        System.out.println(String.format("[%d] position: x=%02.2f, y=%02.2f, z=%02.2f",
                count, position[X], position[Y], position[Z]));
        writePixelWithinLimit(canvas, position, color_red);
    }

    private static void usage() {
        System.out.println("Usage: java net.rednode.rtc.SimpleCanonToPPM <outputfile>");
    }

    public static void main(String[] args)
    throws IOException {
        System.out.println("Simple Canon firing simulation to PPM");
        if (args.length != 1) {
            usage();
            System.exit(1);
        }

        Path outfile = Paths.get(args[0]);

        double[][][] canvas = Canvas.create(1024, 768);
        Projectile p = new Projectile(point(0, 1, 0),
                multiply(normalize(vector(1, 1.8, 0)), 11.25));
        int count = 0;
        while (p.position[Y] > 0) {
            printPosition(canvas, count++, p.position);
            tick(p);
        }
        Canvas.writePPMFile(canvas, outfile);
    }
}
