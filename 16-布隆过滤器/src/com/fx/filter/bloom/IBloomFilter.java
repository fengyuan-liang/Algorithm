package com.fx.filter.bloom;

/**
 * @author Eureka <liangfengyuan1024@gmail.com>
 * @since 2024/11/17 22:17
 */
public interface IBloomFilter<Element> {
    /**
     * add element to bloom
     */
    void put(Element element);

    /**
     * judge an element contains in bloom
     */
    boolean contains(Element element);
}
