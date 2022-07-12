package com.fx.set;

import com.fx.IMap.Map;
import com.fx.Map.TreeMap;

import javax.xml.bind.Element;

/**
 * <p>
 *
 * </p>
 *
 * @since: 2022/7/12 22:45
 * @author: 梁峰源
 */
public class TreeSet<E> implements Set<E> {
    Map<E, Object> map = new TreeMap<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    @Override
    public void add(E element) {
        map.put(element, null);
    }

    @Override
    public void remove(E element) {
        map.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        map.traversal(new Map.Visitor<E, Object>() {
            @Override
            public boolean visit(E key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}
