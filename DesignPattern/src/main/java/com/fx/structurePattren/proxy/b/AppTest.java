package com.fx.structurePattren.proxy.b;

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

/**
 *
 */
class MyHandle implements InvocationHandler {

    private final Object target;

    public MyHandle(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(String.format("方法名为：%s，参数为：%s", method.getName(), Arrays.toString(args)));
        long start = System.currentTimeMillis();
        // 利用反射调用真实方法
        Object result = method.invoke(target, args);
        System.out.println(String.format("耗时：%s ms", System.currentTimeMillis() - start));
        // 返回到代理对象的方法调用处
        return result;
    }
}

public class AppTest {
    public static void main(String[] args) {
        ICalculate iCalculate = new CalculateImpl();
        // 创建代理对象，需要三个参数，分别是
        Class<?>[] interfaceClass = {ICalculate.class};
        ICalculate proxy = (ICalculate) Proxy.newProxyInstance(AppTest.class.getClassLoader(), interfaceClass, new MyHandle(iCalculate));
        System.out.println(proxy.add(1, 3));
        System.out.println(proxy.reduce(1, 3));
        System.out.println(proxy.multiply(1, 3));
        System.out.println(proxy.divide(1, 3));
    }
}
