package com.fx.structurePattren.decoratorpattern.a;

import lombok.Data;

@Data
abstract class Beverage {
    private String description;

    public Beverage(String description) {
        this.description = description;
    }

    // 花费
    public abstract double cost();
}

class Decaf extends Beverage {

    public Decaf() {
        super("无糖咖啡");
    }

    @Override
    public double cost() {
        return 1;
    }
}

class Espresso extends Beverage {

    public Espresso() {
        super("浓缩咖啡");
    }

    @Override
    public double cost() {
        return 5;
    }
}

class DrakRoast extends Beverage {

    public DrakRoast() {
        super("焦糖咖啡");
    }

    @Override
    public double cost() {
        return 15;
    }
}

class HouseBlend extends Beverage {

    public HouseBlend() {
        super("混合咖啡");
    }

    @Override
    public double cost() {
        return 10;
    }
}

///////////////  请注意上述代码已经由作者写死了，无法改变  ///////////////

public class AppTest {
    public static void main(String[] args) {
        Beverage decaf = new Decaf();
        Beverage espresso = new Espresso();
        Beverage drakRoast = new DrakRoast();
        Beverage houseBlend = new HouseBlend();
        System.out.println(String.format("咖啡描述：%s，咖啡价格：%.2f", decaf.getDescription(), decaf.cost()));
        System.out.println(String.format("咖啡描述：%s，咖啡价格：%.2f", espresso.getDescription(), espresso.cost()));
        System.out.println(String.format("咖啡描述：%s，咖啡价格：%.2f", drakRoast.getDescription(), drakRoast.cost()));
        System.out.println(String.format("咖啡描述：%s，咖啡价格：%.2f", houseBlend.getDescription(), houseBlend.cost()));
    }
}
