package com.fx.designprinciples.demeter.negative;


class Compute {
    private void saveData() {
        System.out.println("正在保存数据");
    }

    private void killProcess() {
        System.out.println("正在关闭程序");
    }

    private void closeScreen() {
        System.out.println("正在关闭屏幕");
    }

    private void powerOff() {
        System.out.println("正在断电");
    }

    public void shutDownCompute() {
        this.saveData();
        this.killProcess();
        this.closeScreen();
        this.powerOff();
    }
}

class Person {
    Compute compute = new Compute();

    public void shutDown() {
        compute.shutDownCompute();
    }
}

public class AppTest {
}
