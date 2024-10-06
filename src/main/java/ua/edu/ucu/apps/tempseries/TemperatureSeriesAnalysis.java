package ua.edu.ucu.apps.tempseries;
public class TemperatureSeriesAnalysis {


    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
        private int len = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temp : temperatureSeries) {
            if (temp < -273) {
                throw new InputMismatchException("Temperature can't be less than -273°C.");
            }
        }
        this.temperatureSeries = temperatureSeries.clone();
        this.size = temperatureSeries.length;
    }

    public double average() {
        if (len == 0) {
            throw new IllegalArgumentException("No data");
        } else {
            double sum = 0;
            for (double i : temperatureSeries) {
                sum += i;
            }
        return sum/len;
        }
    }

    public double deviation() {
        if (len == 0) {
            throw new IllegalArgumentException("No data");
        } else {
            double avg = average();
            double dif = 0;
            for (double i : temperatureSeries) {
                dif += (i - avg) * (i - avg);
            }
        return Math.sqrt(dif/size);
        }

    }

    public double min() {
        if (len == 0) {
            throw new IllegalArgumentException("No data");
        } else {
            double min = temperatureSeries[0];
            for (double i : temperatureSeries) {
                if (i < min) {
                    min = i;
                }
            }
            return min;
        }

    }

    public double max() {
        if (len == 0) {
            throw new IllegalArgumentException("No data");
        } else {
            double max = temperatureSeries[0];
            for (double i : temperatureSeries) {
                if (i > max) {
                    max = i;
                }
            }
            return max;
        }
    }

    public double findTempClosestToZero() {
        if (len == 0) {
            throw new IllegalArgumentException("No data");
        } else {
            double closestToZero = Math.abs(temperatureSeries[0]);
            for (double i : temperatureSeries) {
                if (Math.abs(i) < closestToZero) {
                    closestToZero = i;
                }
            }
            return Math.abs(closestToZero);
        }
    }

    public double findTempClosestToValue(double tempValue) {
        if (len == 0) {
            throw new IllegalArgumentException("No data");
        } else {
            double closestToValue = Math.abs(tempValue - temperatureSeries[0]);
            for (double i : temperatureSeries) {
                if (Math.abs(tempValue - i) < closestToValue) {
                    closestToValue = i;
                }
            }
            return Math.abs(closestToValue);
        }
    }

    public double[] findTempsLessThen(double tempValue) {
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
                res[j] = temperatureSeries[i];
                j++; 
            }
        }
        return res;

    }

    public double[] findTempsGreaterThen(double tempValue) {
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
                res[j] = temperatureSeries[i];
                j++; 
            }
        }
        return res;
    }

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
                res[j] = temperatureSeries[i];
                j++; 
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
        Arrays.sort(sorted);
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
            if (temp < -273) {
                throw new InputMismatchException("Temperature can't be less than -273°C.");
            }
        }
        if (size + temps.length > temperatureSeries.length) {
            int newCapacity = Math.max(size + temps.length, temperatureSeries.length * 2);
            double[] newTemperatureSeries = new double[newCapacity];

            for (int i = 0; i < size; i++) {
                newTemperatureSeries[i] = temperatureSeries[i];
            }
            temperatureSeries = newTemperatureSeries;
        }

        for (int i = 0; i < temps.length; i++) {
            temperatureSeries[size + i] = temps[i];
        }
        size += temps.length;
        return size;
    }
}
