package com.fx.structurePattren.adapter.a;


class Calc {
    public int add(int a, int b) {
        return a + b;
    }
}

class CalcAdapter {
    private final Calc calc;

    public CalcAdapter(Calc calc) {
        this.calc = calc;
    }

    public int add(int a, int b, int c) {
        return calc.add(calc.add(a, b), c);
    }
}

public class AppTest {
    public static void main(String[] args) {
        CalcAdapter calcAdapter = new CalcAdapter(new Calc());
        System.out.println(calcAdapter.add(1, 4, 8));
    }
}
