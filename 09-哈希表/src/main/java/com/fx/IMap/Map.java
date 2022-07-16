package com.fx.IMap;

import java.util.function.BiConsumer;

/**
 * Map 接口
 * @param <K>
 * @param <V>
 * @since 2022年7月12日
 * @author 梁峰源
 */
public interface Map<K, V> {
    int size();

    boolean isEmpty();

    void clear();

    V put(K key, V value);

    V get(K key);

    V remove(K key);

    void forEach(BiConsumer<? super K, ? super V> action);

    /**
     * 是否包含key
     */
    boolean containsKey(K key);

    boolean containsValue(V value);

    void traversal(Visitor<K, V> visitor);

    abstract class Visitor<K, V> {
        public boolean stop;
        public abstract boolean visit(K key, V value);
    }
}
