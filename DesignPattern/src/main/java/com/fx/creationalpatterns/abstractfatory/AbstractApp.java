package com.fx.creationalpatterns.abstractfatory;

/******************  抽象产品   *********************/
// 食物抽象类
interface Food {
    void eat();
}
// 饮料抽象类
interface Drink{
    void drink();
}
/******************  具体产品   *********************/
// 食物的具体产品
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

class Cola implements Drink{
    @Override
    public void drink() {
        System.out.println("喝可口可乐...");
    }
}

class IcePeak implements Drink{
    @Override
    public void drink() {
        System.out.println("喝冰峰...");
    }
}

/******************  抽象工厂类   *********************/
interface AbstractFactory {
    Food getFood();
    Drink getDrink();
}

/******************  实例工厂类   *********************/

class KFCFactory implements AbstractFactory {
    @Override
    public Food getFood() {
        return new Hamburger();
    }
    @Override
    public Drink getDrink() {
        return new Cola();
    }
}

class RichNoodleFactory implements AbstractFactory {
    @Override
    public Food getFood() {
        return new RichNoodle();
    }
    @Override
    public Drink getDrink() {
        return new IcePeak();
    }
}



public class AbstractApp {
    public static void main(String[] args) {
        // 拿到产生产品的工程
        AbstractFactory abstractFactory = new KFCFactory();
        Food food = abstractFactory.getFood();
        food.eat();
        Drink drink = abstractFactory.getDrink();
        drink.drink();
    }
}
