package ua.edu.ucu.apps.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final double ABSOLUTE_ZERO_CELSIUS = -273.15; // Constant for absolute zero
    private double[] temperatureSeries; // Array to hold temperature data
    private int size; // Current size of the array

    // Constructor for empty temperature series
    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
        this.size = 0;
    }

    // Constructor with initial temperature series
    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temp : temperatureSeries) {
            if (temp < ABSOLUTE_ZERO_CELSIUS) {
                throw new InputMismatchException("Temperature can't be less than -273°C.");
            }
        }
        this.temperatureSeries = temperatureSeries.clone();
        this.size = temperatureSeries.length;
    }

    // Method to calculate the average temperature
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

    // Method to calculate the standard deviation of temperatures
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

    // Method to find the minimum temperature
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

    // Method to find the maximum temperature
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

    // Method to find the temperature closest to zero
    public double findTempClosestToZero() {
        if (size == 0) {
            throw new IllegalArgumentException("No data");
        }
        double closestToZero = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (Math.abs(temp) < Math.abs(closestToZero)) {
                closestToZero = temp;
            }
        }
        return closestToZero;
    }

    // Method to find the temperature closest to a specified value
    public double findTempClosestToValue(double tempValue) {
        if (size == 0) {
            throw new IllegalArgumentException("No data");
        }
        double closestToValue = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (Math.abs(temp - tempValue) < Math.abs(closestToValue - tempValue)) {
                closestToValue = temp;
            }
        }
        return closestToValue;
    }

    // Method to find temperatures less than a specified value
    public double[] findTempsLessThan(double tempValue) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (temperatureSeries[i] < tempValue) {
                count++;
            }
        }
        double[] res = new double[count];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (temperatureSeries[i] < tempValue) {
                res[j++] = temperatureSeries[i];
            }
        }
        return res;
    }

    // Method to find temperatures greater than a specified value
    public double[] findTempsGreaterThan(double tempValue) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (temperatureSeries[i] > tempValue) {
                count++;
            }
        }
        double[] res = new double[count];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (temperatureSeries[i] > tempValue) {
                res[j++] = temperatureSeries[i];
            }
        }
        return res;
    }

    // Method to find temperatures within a specified range
    public double[] findTempsInRange(double lowerBound, double upperBound) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (temperatureSeries[i] > lowerBound && temperatureSeries[i] < upperBound) {
                count++;
            }
        }
        double[] res = new double[count];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (temperatureSeries[i] > lowerBound && temperatureSeries[i] < upperBound) {
                res[j++] = temperatureSeries[i];
            }
        }
        return res;
    }

    // Method to reset the temperature series
    public void reset() {
        temperatureSeries = new double[0];
        size = 0;
    }

    // Method to sort temperatures using bubble sort
    public double[] sortTemps() {
        double[] sorted = new double[size];
        for (int i = 0; i < size; i++) {
            sorted[i] = temperatureSeries[i];
        }

        // Bubble sort implementation
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (sorted[j] > sorted[j + 1]) {
                    double temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }

    // Method to return summary statistics
    public TempSummaryStatistics summaryStatistics() {
        if (size == 0) {
            throw new IllegalArgumentException("No data");
        }
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }
}



