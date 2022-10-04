package com.fx.designprinciples.interfaceioslation.negtive;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/10/2 21:35
 */
public class AppTest {

    interface Eatable {
        void eat();
    }

    interface Swimable {
        void swim();
    }

    interface Flyable {
        void fly();
    }

    static class Dog implements Eatable, Swimable {

        @Override
        public void eat() {
            System.out.println("小狗在吃东西");
        }

        @Override
        public void swim() {
            System.out.println("小狗会狗刨");
        }
    }
}
