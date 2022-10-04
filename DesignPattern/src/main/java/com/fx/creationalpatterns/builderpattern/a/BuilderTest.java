package com.fx.creationalpatterns.builderpattern.a;

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


public class BuilderTest {
    public static void main(String[] args) {
        Compute compute = new Compute()
                .setCpu("i7-10700")
                .setGpu("3080 Ti")
                .setMemory("32G")
                .setHd("1T");
        System.out.println(compute);
    }
}
