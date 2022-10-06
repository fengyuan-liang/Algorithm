package com.fx.structurePattren.proxy.c;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

interface ICalculate {
    int add(int x, int y);

    int reduce(int x, int y);

    int multiply(int x, int y);

    double divide(int x, int y);
}

class CalculateImpl implements ICalculate {
    public int add(int x, int y) {
        return (x + y);
    }

    public int reduce(int x, int y) {
        return (x - y);
    }

    public int multiply(int x, int y) {
        return x * y;
    }

    @Override
    public double divide(int x, int y) {
        return x / y;
    }
}


class MyHandle implements InvocationHandler {

    private final Object target;
    private final Aspect aspect;

    public MyHandle(Object target, Aspect aspect) {
        this.target = target;
        this.aspect = aspect;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 方法执行前的钩子函数
        aspect.before(target, method, args);
        Object result = method.invoke(target, args);
        // 方法执行后的钩子函数
        aspect.after(target, method, args, result);
        // 返回到代理对象的方法调用处
        return result;
    }

}


interface Aspect {
    void before(Object target, Method method, Object[] args);

    void after(Object target, Method method, Object[] args, Object returnVal);
}

class MyProxy {
    public static Object getProxy(Object cls, Aspect aspect) {
        // 拿到代理对象实现的所有接口字节码
        Class<?>[] interfaceClass = cls.getClass().getInterfaces();
        return Proxy.newProxyInstance(cls.getClass().getClassLoader(), interfaceClass, new MyHandle(cls, aspect));
    }
}

public class AppTest {
    public static void main(String[] args) {
        ICalculate iCalculate = new CalculateImpl();
        // 创建代理对象，需要三个参数，分别是
        ICalculate proxy = (ICalculate) MyProxy.getProxy(iCalculate, new Aspect() {
            @Override
            public void before(Object target, Method method, Object[] args) {
                System.out.println(String.format("方法名为：%s，参数为：%s", method.getName(), Arrays.toString(args)));
            }

            @Override
            public void after(Object target, Method method, Object[] args, Object returnVal) {
                System.out.println(String.format("返回结果为：%s", returnVal.toString()));
            }
        });
        System.out.println(proxy.add(1, 3));
        System.out.println(proxy.reduce(1, 3));
        System.out.println(proxy.multiply(1, 3));
        System.out.println(proxy.divide(1, 3));
    }
}
