package nl.bioinf.jur.webApp.model;

import java.text.DecimalFormat;

public class HeartRateZone {
    protected double min;
    protected double max;

    public HeartRateZone(double min, double max) {
        this.min = Math.round(min);
        this.max = Math.round(max);
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}

