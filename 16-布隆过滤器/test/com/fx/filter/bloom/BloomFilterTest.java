package com.fx.filter.bloom;

import org.junit.jupiter.api.Test;

/**
 * @author Eureka <liangfengyuan1024@gmail.com>
 * @since 2024/11/17 22:36
 */
public class BloomFilterTest {

    @Test
    public void testBloomMathInfo() {
        BloomFilter<Integer> bf = new BloomFilter<>(1_000_000_000, 0.01);
        int n = 100;
        System.out.println(~n);
    }
}
