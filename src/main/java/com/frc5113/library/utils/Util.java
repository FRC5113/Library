package com.frc5113.library.utils;

import java.util.List;

import com.frc5113.library.primative.SmartTimedRobot;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Timer;

/**
 * Contains basic functions that are used often.
 *
 * @author Richard Lin (254)
 * @author Brandon Gonzalez (?)
 * @author Tom Bottiglieri (254)
 * @author Vladimir Bondar (5113)
 */
public class Util {
    // Prevent this class from being instantiated.
    private Util() {
    }

    /**
     * Limits the given input to the given magnitude.
     */
    public static double limit(double v, double limit) {
        return limit(v, limit, -limit);
        //return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
    }

    /**
     * limits input to the max and min
     * @param v - input
     * @param max - max value
     * @param min - min value
     * @return
     */
    public static double limit(double v, double max, double min){
        return (v > max) ? max : (Math.max(v, min));
    }

    public static String joinStrings(String delim, List<?> strings) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); ++i) {
            sb.append(strings.get(i).toString());
            if (i < strings.size() - 1) {
                sb.append(delim);
            }
        }
        return sb.toString();
    }

    /**
     * Get the time
     * @return Number of seconds since the start of the robot
     */
    public static double getTime(){
        return Timer.getFPGATimestamp();
    }

    public static boolean closeTo(double a, double b, double epsilon) {
        return epsilonEquals(a,b,epsilon);
    }

    public static boolean epsilonEquals(double a, double b, double epsilon) {
        return (a - epsilon <= b) && (a + epsilon >= b);
    }

    public static boolean allCloseTo(List<Double> list, double value, double epsilon) {
        boolean result = true;
        for (Double value_in : list) {
            result &= epsilonEquals(value_in, value, epsilon);
        }
        return result;
    }

    public static double standardDeviation(double[] arr) {
        double mean = 0.0;
        double[] temp = new double[arr.length];

        mean = mean(arr);

        for (int i = 0; i < temp.length; i++) {
            temp[i] = Math.pow((arr[i] - mean),2);
        }

        return Math.sqrt(mean(temp));
    }

    public static double mean(double[] arr) {
        double sum = 0.0;

        for (double v : arr) {
            sum += v;
        }

        return sum/arr.length;
    }

    public static double max(double[] arr) {
        double max = 0;
        for (double v : arr) {
            if (v > max) {
                max = v;
            }
        }

        return max;
    }

    public static double powKeepSign(double v, double p) {
        return Math.signum(v)*Math.abs(Math.pow(v, p));
    }
}