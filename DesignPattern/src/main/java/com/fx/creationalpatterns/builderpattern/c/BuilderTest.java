package com.fx.creationalpatterns.builderpattern.c;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
class Compute {
    private String cpu;
    private String gpu;
    private String memory;
    private String hd;
}

interface IcomputeBuilder{
    Compute builder();
}

// 高级配置
class HighComputeBuilder implements IcomputeBuilder{
    private Compute compute = new Compute();

    // 构建方法
    public Compute builder() {
        return compute.setCpu("i7-10700")
                .setGpu("3080 Ti")
                .setMemory("32G")
                .setHd("1T");
    }
}

class MiddleComputeBuilder implements IcomputeBuilder{
    private Compute compute = new Compute();

    // 构建方法
    public Compute builder() {
        return compute.setCpu("i5-9500")
                .setGpu("2080 Ti")
                .setMemory("16G")
                .setHd("1T");
    }
}

class LowComputeBuilder implements IcomputeBuilder{
    private Compute compute = new Compute();

    // 构建方法
    public Compute builder() {
        return compute.setCpu("i3-8500")
                .setGpu("1080 Ti")
                .setMemory("8G")
                .setHd("500G");
    }
}

public class BuilderTest {
    public static void main(String[] args) {
        // 创建一个建造者
        IcomputeBuilder highBuilder  = new HighComputeBuilder();
        IcomputeBuilder middleBuilder = new MiddleComputeBuilder();
        IcomputeBuilder lowBuilder = new LowComputeBuilder();
        // 创建最厉害的电脑
        Compute highCompute = highBuilder.builder();
        System.out.println(highCompute);
        // 中等的电脑
        Compute middleCompute = middleBuilder.builder();
        System.out.println(middleCompute);
        // 一般的电脑
        Compute lowCompute = lowBuilder.builder();
        System.out.println(lowCompute);
    }
}
