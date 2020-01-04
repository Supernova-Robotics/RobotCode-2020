package frc.math;

public class MathFunctions {
    public static double clip(double value, double max, double min){
        return Math.min(Math.max(value, min), max);
    }

    public static double clipToOne(double value){
        return clip(value, 1, -1);
    }
}