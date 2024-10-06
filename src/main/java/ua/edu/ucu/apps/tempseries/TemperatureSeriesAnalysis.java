package ua.edu.ucu.apps.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private int size;
    private static final double ABSOLUTE_ZERO_CELSIUS = -273.15;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
        this.size = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temp : temperatureSeries) {
            if (temp < ABSOLUTE_ZERO_CELSIUS) {
                throw new InputMismatchException("Temperature can't be less than -273°C.");
            }
        }
        this.temperatureSeries = temperatureSeries.clone();
        this.size = temperatureSeries.length;
    }

    public double average() {
        if (size == 0) {
            throw new IllegalArgumentException("No data");
        }
        double sum = 0;
        for (double temp : temperatureSeries) {
            sum += temp;
        }
        return sum / size;
    }

    public double deviation() {
        if (size == 0) {
            throw new IllegalArgumentException("No data");
        }
        double avg = average();
        double sumOfSquares = 0;
        for (double temp : temperatureSeries) {
            sumOfSquares += (temp - avg) * (temp - avg);
        }
        return Math.sqrt(sumOfSquares / size);
    }

    public double min() {
        if (size == 0) {
            throw new IllegalArgumentException("No data");
        }
        double min = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (temp < min) {
                min = temp;
            }
        }
        return min;
    }

    public double max() {
        if (size == 0) {
            throw new IllegalArgumentException("No data");
        }
        double max = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        if (size == 0) {
            throw new IllegalArgumentException("No data");
        }
        double closestToZero = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (Math.abs(temp) < Math.abs(closestToZero) || 
                (Math.abs(temp) == Math.abs(closestToZero) && temp > closestToZero)) {
                closestToZero = temp;
            }
        }
        return closestToZero;
    }

    public double findTempClosestToValue(double tempValue) {
        if (size == 0) {
            throw new IllegalArgumentException("No data");
        }
        double closestToValue = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (Math.abs(temp - tempValue) < Math.abs(closestToValue - tempValue) || 
                (Math.abs(temp - tempValue) == Math.abs(closestToValue - tempValue) && temp > closestToValue)) {
                closestToValue = temp;
            }
        }
        return closestToValue;
    }

    public double[] findTempsLessThen(double tempValue) {
        int count = 0;
        for (double temp : temperatureSeries) {
            if (temp < tempValue) {
                count++;
            }
        }
        double[] res = new double[count];
        int j = 0;
        for (double temp : temperatureSeries) {
            if (temp < tempValue) {
                res[j++] = temp;
            }
        }
        return res;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int count = 0;
        for (double temp : temperatureSeries) {
            if (temp > tempValue) {
                count++;
            }
        }
        double[] res = new double[count];
        int j = 0;
        for (double temp : temperatureSeries) {
            if (temp > tempValue) {
                res[j++] = temp;
            }
        }
        return res;
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        int count = 0;
        for (double temp : temperatureSeries) {
            if (temp > lowerBound && temp < upperBound) {
                count++;
            }
        }
        double[] res = new double[count];
        int j = 0;
        for (double temp : temperatureSeries) {
            if (temp > lowerBound && temp < upperBound) {
                res[j++] = temp;
            }
        }
        return res;
    }

    public void reset() {
        temperatureSeries = new double[0];
        size = 0;
    }

    public double[] sortTemps() {
        double[] sorted = temperatureSeries.clone();
        java.util.Arrays.sort(sorted);
        return sorted;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (size == 0) {
            throw new IllegalArgumentException("No data");
        }
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        for (double temp : temps) {
            if (temp < ABSOLUTE_ZERO_CELSIUS) {
                throw new InputMismatchException("Temperature can't be less than -273°C.");
            }
        }
        if (size + temps.length > temperatureSeries.length) {
            int newCapacity = Math.max(size + temps.length, temperatureSeries.length * 2);
            double[] newTemperatureSeries = new double[newCapacity];
            System.arraycopy(temperatureSeries, 0, newTemperatureSeries, 0, size);
            temperatureSeries = newTemperatureSeries;
        }
        System.arraycopy(temps, 0, temperatureSeries, size, temps.length);
        size += temps.length;
        return size;
    }
}



