package org.example;

public class BStarTree {
    BStarTreeNode root;
    int t;

    public BStarTree(int t) {
        this.root = null;
        this.t = t;
    }

    public void insert(int k, int[] counter) {
        if (root == null) {
            root = new BStarTreeNode(t, true);
            root.keys[0] = k;
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) {
                BStarTreeNode s = new BStarTreeNode(t, false);
                s.children[0] = root;
                s.splitChild(0, root);
                int i = 0;
                if (s.keys[0] < k) i++;
                s.children[i].insertNonFull(k, counter);
                root = s;
            } else {
                root.insertNonFull(k, counter);
            }
        }
    }

    public boolean search(int k, int[] counter) {
        if (root == null) return false;
        return root.search(k, counter) != null;
    }

    public void remove(int k, int[] counter) {
        if (root == null) return;
        root.remove(k, counter);
    }
}
