package com.company.task15;

public class Averager {
    private final double total;
    private final double count;

    public Averager() {
        this.total = 0;
        this.count = 0;
    }

    public Averager(double total, double count) {
        this.total = total;
        this.count = count;
    }

    public double average() {
        return count > 0 ? ((double) total) / count : 0;
    }

    public Averager accept(double i) {
        return new Averager(total + i, count + 1);
    }

    public Averager combine(Averager other) {
        return new Averager(total + other.total, count + other.count);
    }
}
