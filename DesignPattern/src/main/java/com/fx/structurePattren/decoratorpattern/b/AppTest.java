package com.fx.structurePattren.decoratorpattern.b;

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

/**
 * 装饰器模式<br/>
 * 这里我们让调料类继承自饮料类，显然违背了继承中的"is a"关系，但是在装饰器模式中这个原则就是需要违背<br/>
 * 尽管调料不是饮料，但是为了解决问题，我们也只能让调料去继承饮料
 */
abstract class Condiment extends Beverage {
    // 不仅需要继承饮料还需要关联饮料，让调料类关联饮料
    protected Beverage beverage;

    public Condiment(Beverage beverage) {
        super("调料");
        this.beverage = beverage;
    }
}

class Milk extends Condiment {
    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.2;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " 牛奶";
    }
}

class Mocha extends Condiment {
    public Mocha(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.5;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " 摩卡";
    }
}

public class AppTest {
    public static void main(String[] args) {
        Beverage decaf = new Decaf();
        // 现在想要加牛奶
        Milk milk = new Milk(decaf);
        // 加摩卡
        Mocha mocha = new Mocha(milk);
        System.out.println(String.format("咖啡描述：%s，咖啡价格：%.2f", milk.getDescription(), milk.cost()));
        System.out.println(String.format("咖啡描述：%s，咖啡价格：%.2f", mocha.getDescription(), mocha.cost()));
    }
}
