package com.fx.structurePattren.proxy.a;

interface Calculate {
    int add(int x, int y);

    int reduce(int x, int y);

    int multiply(int x, int y);

    double divide(int x, int y);
}

class CalculateImpl implements Calculate {
    public int add(int x, int y) {
        return (x + y);
    }

    public int reduce(int x, int y) {
        return (x - y);
    }

    public int multiply(int x, int y) {
        return x * y;
    }

    @Override
    public double divide(int x, int y) {
        return x / y;
    }
}

public class AppTest {
    public static void main(String[] args) {
        Calculate calculate = new CalculateImpl();
        System.out.println(calculate.add(1, 4));
        System.out.println(calculate.reduce(1, 4));
        System.out.println(calculate.multiply(1, 4));
        System.out.println(calculate.divide(1, 4));
    }
}
