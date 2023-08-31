package com.fx.trie;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源 <fengyuan-liang@foxmail.com>
 * @since 2023/6/24 14:26
 */
public interface  ITrie<V> {
    int size();

    boolean isEmpty();

    void clear();

    boolean contains(String str);

    V get(String key);

    V add(String str, V value);

    V remove(String str);

    boolean starsWith(String prefix);
}
