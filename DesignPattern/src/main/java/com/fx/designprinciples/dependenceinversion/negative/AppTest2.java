package com.fx.designprinciples.dependenceinversion.negative;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/10/2 22:44
 */
public class AppTest2 {
}


class A {

}

class B {
    void func(A a1){
        A a2 = new A();
    }
}



