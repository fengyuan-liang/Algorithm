package com.fx.filter.bloom;

import java.util.Objects;

/**
 * @author Eureka <liangfengyuan1024@gmail.com>
 * @since 2024/11/17 22:16
 */
public class BloomFilter<E> implements IBloomFilter<E> {

    /**
     * 二进制数量长度
     */
    private long bitSize;

    /**
     * 二进制向量
     */
    private long[] bits;

    /**
     * hash函数的个数
     */
    private int hashSize;

    /**
     * @param n 数据规模
     * @param p 误判率 取值范围 (0, 1)
     */
    public BloomFilter(long n, double p) {
        if (n <= 0 || p <= 0 || p >= 1) {
            throw new IllegalArgumentException("wrong params");
        }
        double ln2 = Math.log(2);
        // 求出二进制向量的长度
        bitSize = (long) Math.max(1, - (n * Math.log(p)) / (ln2 * ln2));

        // 检查 bitSize 是否超过合理范围
        if (bitSize > (long) Integer.MAX_VALUE * Long.SIZE) {
            throw new IllegalArgumentException("bitSize is too large");
        }

        // 哈希函数的个数
        hashSize = (int) Math.min(bitSize, (bitSize * ln2 / n));

        // 计算 bits 数组的长度
        int arrayLength = (int) ((bitSize + Long.SIZE - 1) / Long.SIZE);
        bits = new long[arrayLength];
    }


    @Override
    public void put(E element) {
        checkIllegalParams(element);
        int hash1 = element.hashCode();
        int hash2 = hash1 >>> 1;
        for (int i = 0; i < hashSize; i++) {
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            // 更新位数组
            bits[combinedHash / Long.SIZE] |= 1L << (combinedHash % Long.SIZE);
        }
    }

    @Override
    public boolean contains(E element) {
        checkIllegalParams(element);
        int hash1 = element.hashCode();
        int hash2 = hash1 >>> 1;
        for (int i = 0; i < hashSize; i++) {
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            // 检查位数组
            if ((bits[combinedHash / Long.SIZE] & (1L << (combinedHash % Long.SIZE))) == 0) {
                return false;
            }
        }
        return true;
    }

    private void checkIllegalParams(E element) {
        if (Objects.isNull(element)) {
            throw new IllegalArgumentException("wrong params");
        }
    }
}