package com.fx.designprinciples.dependenceinversion.negative;


class Person {
    public void feed(Dog dog) {
        System.out.println("开始喂dog...");
    }

    public void feed(Cat cat) {
        System.out.println("开始喂Cat...");
    }
}

class Dog {
    public void eat() {
        System.out.println("狗啃骨头");
    }
}

class Cat {
    public void eat() {
        System.out.println("小猫吃鱼");
    }
}

// ================================================================
public class AppTest {
    public static void main(String[] args) {
        Person person = new Person();
        Dog dog = new Dog();
        Cat cat = new Cat();
        // 喂狗
        person.feed(dog);
        // 喂猫
        person.feed(cat);
    }
}





