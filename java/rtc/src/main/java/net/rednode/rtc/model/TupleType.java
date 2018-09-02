package net.rednode.rtc.model;

import net.rednode.rtc.Tuple;

public enum TupleType {

    Vector(0.0),
    Point(1.0);


    public final double typenum;

    TupleType(double typenum) {
        this.typenum = typenum;
    }

    public static TupleType determine(double[] tuple) {
        if (Math.abs(tuple[3] - TupleType.Vector.typenum) < Tuple.epsilon)
            return TupleType.Vector;
        if (Math.abs(tuple[3] - TupleType.Point.typenum) < Tuple.epsilon)
            return TupleType.Point;
        throw new IllegalArgumentException("Unknown Tuple Type");
    }

}
