package com.fx.designprinciples.liskovsubstitution.negtive;


class Father {
    private String name;
    public double age;

    public void sayHello() {
        System.out.println("hello father");
    }
}

class Son extends Father {

    @Override
    public void sayHello() {
        System.out.println("son hello" + age);
    }
}


public class AppTest {
    public static void main(String[] args) {
        Father father = new Son();
        father.sayHello();
    }
}



