package com.cacard.alg;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 二叉树，广度优先，发现第N层多少个。
 * 递归，以及引用值更新（用AtomicInteger实现引用值更新）
 */
public class TT1 {

    public static void main(String[] args) {
        Node root = create();
        AtomicInteger count = new AtomicInteger();
        new TT1().find(root, 1, 3, count);
        System.out.println(count.get());
    }

    private void find(Node newRoot, int newRootLayer, int targetLayer, AtomicInteger findCount) {
        if (newRoot == null) {
            return;
        }

        if (newRootLayer == targetLayer) {
            System.out.println("find");
            findCount.incrementAndGet();
        }

        int nextLayer = newRootLayer + 1;
        find(newRoot.left, nextLayer, targetLayer, findCount);
        find(newRoot.right, nextLayer, targetLayer, findCount);
    }

    static Node create() {
        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);

        root.right.left = new Node(5);
        root.right.right = new Node(6);

        return root;
    }

    static class Node {
        public Node left;
        public Node right;
        public int val;

        public Node() {
        }

        public Node(int v) {
            val = v;
        }
    }

}
