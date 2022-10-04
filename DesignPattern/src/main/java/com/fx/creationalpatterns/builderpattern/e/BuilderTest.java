package com.fx.creationalpatterns.builderpattern.e;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
class Computer {
    private String cpu;
    private String gpu;
    private String memory;
    private String hd;
}

interface IComputerBuilder {
    IComputerBuilder cpu();

    IComputerBuilder gpu();

    IComputerBuilder memory();

    IComputerBuilder hd();

    Computer builder();
}

// 隐藏指挥命令的细节
class Director {
    public Computer build(IComputerBuilder computerBuilder) {
        // 指挥builder进行组装
        return computerBuilder.cpu().gpu().memory().hd().builder();
    }
}

// 高级配置
class HighComputerBuilder implements IComputerBuilder {
    private Computer computer = new Computer();

    @Override
    public IComputerBuilder cpu() {
        computer.setCpu("i7-10700");
        return this;
    }

    @Override
    public IComputerBuilder gpu() {
        computer.setGpu("3080 Ti");
        return this;
    }

    @Override
    public IComputerBuilder memory() {
        computer.setMemory("32G");
        return this;
    }

    @Override
    public IComputerBuilder hd() {
        computer.setHd("1T");
        return this;
    }

    // 构建方法
    public Computer builder() {
        return computer;
    }
}


public class BuilderTest {
    public static void main(String[] args) {
        // 创建一个建造者
        IComputerBuilder highBuilder = new HighComputerBuilder();
        // 创建指挥者
        Director director = new Director();
        // 由指挥者进行指挥
        Computer highComputer = director.build(highBuilder);
        System.out.println(highComputer);
    }
}
