package com.fx.creationalpatterns.builderpattern.d;

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

interface IcomputeBuilder {
    IcomputeBuilder cpu();

    IcomputeBuilder gpu();

    IcomputeBuilder memory();

    IcomputeBuilder hd();

    Compute builder();
}

// 高级配置
class HighComputeBuilder implements IcomputeBuilder {
    private Compute compute = new Compute();

    @Override
    public IcomputeBuilder cpu() {
        compute.setCpu("i7-10700");
        return this;
    }

    @Override
    public IcomputeBuilder gpu() {
        compute.setGpu("3080 Ti");
        return this;
    }

    @Override
    public IcomputeBuilder memory() {
        compute.setMemory("32G");
        return this;
    }

    @Override
    public IcomputeBuilder hd() {
        compute.setHd("1T");
        return this;
    }

    // 构建方法
    public Compute builder() {
        return compute;
    }
}


public class BuilderTest {
    public static void main(String[] args) {
        // 创建一个建造者
        IcomputeBuilder highBuilder = new HighComputeBuilder();
        // 创建最厉害的电脑
        Compute highCompute = highBuilder.cpu().gpu().memory().hd().builder();
        System.out.println(highCompute);
    }
}
