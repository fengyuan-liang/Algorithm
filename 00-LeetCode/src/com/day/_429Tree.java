package com.day;

import java.util.*;

/**
 * 层序遍历变种
 *
 * @author 梁峰源 <fengyuan-liang@foxmail.com>
 * @since 2024/6/2 11:50
 */
public class _429Tree {
    public static void main(String[] args) {
        Node n1 = new Node(1, new ArrayList<>() {{
            add(new Node(2, new ArrayList<>() {{
                add(new Node(4, new ArrayList<>() {{
                    add(new Node(8));
                    add(new Node(9));
                    add(new Node(10));
                }}));
                add(new Node(5));
            }}));
            add(new Node(3, new ArrayList<>() {{
                add(new Node(6));
                add(new Node(7));
            }}));
        }});
        System.out.println(levelOrder3(n1));
    }

    public static List<List<Integer>> levelOrder2(Node root) {
        if (Objects.isNull(root)) {
            return Collections.emptyList();
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> l1 = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (Objects.isNull(node.children)) {
                continue;
            }
            List<Integer> l2 = new ArrayList<>();
            for (Node child : node.children) {
                queue.offer(child);
                l2.add(child.val);
            }
            l1.add(l2);
        }
        return l1;
    }

    public static List<List<List<Integer>>> levelOrder3(Node root) {
        if (Objects.isNull(root) || root.children.isEmpty()) {
            return Collections.emptyList();
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        List<List<List<Integer>>> l1 = new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{
                    add(root.val);
                }});
            }});
        }};
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<List<Integer>> l2 = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                List<Integer> l3 = new ArrayList<>();
                Node node = queue.poll();
                if (Objects.isNull(node.children)) {
                    continue;
                }
                for (Node child : node.children) {
                    l3.add(child.val);
                    queue.offer(child);
                }
                if (!l3.isEmpty()) {
                    l2.add(l3);
                }

            }
            if (!l2.isEmpty()) {
                l1.add(l2);
            }
        }
        return l1;
    }


    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
