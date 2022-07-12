package com.fx.set;

import com.fx.List.LinkedList;
import com.fx.List.List;

/**
 * <p>
 * 双向链表实现集合
 * </p>
 *
 * @since: 2022/6/29 9:21
 * @author: 梁峰源
 */
public class ListSet<E> implements Set<E> {
    private final List<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        // 集合不能包含重复的元素，更加推荐覆盖元素的方式
//        if(list.contains(element)) return;
        int index = list.indexOf(element);
        // 存在就覆盖，不存在就添加
        if (index!= list.ELEMENT_NOT_FOUND) {
            list.set(index, element);
        }else {
            list.add(element);
        }
    }

    @Override
    public void remove(E element) {
        list.remove(list.indexOf(element));
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        // 如果没有传遍历接口，直接返回
        if (visitor == null) return;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (visitor.visit(list.get(i))) return;
        }
    }
}
