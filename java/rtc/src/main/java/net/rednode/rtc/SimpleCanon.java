package net.rednode.rtc;

import static net.rednode.rtc.Tuple.*;

public class SimpleCanon {

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

    private static void tick(Projectile p) {
        p.position = add(p.position, p.velocity);
        p.velocity = add(p.velocity, world_gravity);
        p.velocity = add(p.velocity, world_wind);
    }

    private static void printPosition(int count, double[] position) {
        System.out.println(String.format("[%d] position: x=%02.2f, y=%02.2f, z=%02.2f",
                count, position[X], position[Y], position[Z]));
    }

    public static void main(String[] args) {
        System.out.println("Simple Canon firing simulation");

        Projectile p = new Projectile(point(0, 1, 0), normalize(vector(2, 20, 0)));

        int count = 0;
        while (p.position[Y] > 0) {
            printPosition(count++, p.position);
            tick(p);
        }
    }
}
