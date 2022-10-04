package com.fx.creationalpatterns.factorymethod;

// 作者做的抽象产品
interface Food {
    void eat();
}

// 作者做的具体产品
class Hamburger implements Food {

    @Override
    public void eat() {
        System.out.println("吃汉堡包...");
    }
}

class RichNoodle implements Food {

    @Override
    public void eat() {
        System.out.println("过桥米线");
    }
}
/** 定义工厂的接口 **/
interface FoodFactory {
    Food getFood();
}

class HamburgerFactory implements FoodFactory {

    @Override
    public Food getFood() {
        return new Hamburger();
    }
}

class RichNoodleFactory implements FoodFactory {

    @Override
    public Food getFood() {
        return new RichNoodle();
    }
}
/** 新的产品 **/
class PorkFeetRice implements Food {

    @Override
    public void eat() {
        System.out.println("吃猪角饭...");
    }
}
/** 生产猪角饭的工厂 **/
class PorkFeetRiceFactory implements FoodFactory {

    @Override
    public Food getFood() {
        return new PorkFeetRice();
    }
}

public class App {
    public static void main(String[] args) {
        // 拿到产生产品的工程
        FoodFactory foodFactory = new PorkFeetRiceFactory();
        Food food = foodFactory.getFood();
        food.eat();
    }
}
