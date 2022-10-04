package com.fx.creationalpatterns.builderpattern.b;

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

// 电脑构建者类，并且必须关联一个产品
class ComputeBuilder {
    private Compute compute = new Compute();

    // 构建方法
    public Compute builder() {
        return compute.setCpu("i7-10700")
                .setGpu("3080 Ti")
                .setMemory("32G")
                .setHd("1T");
    }
}

public class BuilderTest {
    public static void main(String[] args) {
        // 创建一个建造者
        ComputeBuilder computeBuilder = new ComputeBuilder();
        // 创建电脑
        Compute compute = computeBuilder.builder();
        System.out.println(compute);
    }
}
