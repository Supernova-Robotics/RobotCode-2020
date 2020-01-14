package frc.math;

public class MathFunctions {
    public static double clip(double value, double max, double min) {
        // clip the value to the range given
        return Math.min(Math.max(value, min), max);
    }

    public static double clipToOne(double value) {
        // clip to [-1, 1]
        return clip(value, 1, -1);
    }

    public static int fitInterval(double[] thresholds, double value) {
        // return the number of interval the value falls in
        for (int i = 0; i < thresholds.length; i += 1) {
            if (Math.abs(value) < thresholds[i]) {
                return Math.max(0, i - 1);
            }
        }
        return thresholds.length - 1;
    }
}