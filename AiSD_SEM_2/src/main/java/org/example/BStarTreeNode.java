package org.example;

import java.util.*;

class BStarTreeNode {
    int t; // Минимальная степень (размер блока)
    int[] keys;
    int n;
    BStarTreeNode[] children;
    boolean leaf;

    public BStarTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.children = new BStarTreeNode[2 * t];
        this.n = 0;
    }

    public void traverse() {
        for (int i = 0; i < n; i++) {
            if (!leaf) children[i].traverse();
            System.out.print(keys[i] + " ");
        }
        if (!leaf) children[n].traverse();
    }

    public BStarTreeNode search(int k, int[] counter) {
        int i = 0;
        counter[0]++;
        while (i < n && k > keys[i]) {
            i++;
            counter[0]++;
        }
        if (i < n && keys[i] == k) return this;
        if (leaf) return null;
        return children[i].search(k, counter);
    }

    public void insertNonFull(int k, int[] counter) {
        int i = n - 1;
        if (leaf) {
            while (i >= 0 && keys[i] > k) {
                keys[i + 1] = keys[i];
                i--;
                counter[0]++;
            }
            keys[i + 1] = k;
            n++;
        } else {
            while (i >= 0 && keys[i] > k) {
                i--;
                counter[0]++;
            }
            if (children[i + 1].n == 2 * t - 1) {
                splitChild(i + 1, children[i + 1]);
                if (keys[i + 1] < k) i++;
            }
            children[i + 1].insertNonFull(k, counter);
        }
    }

    public void splitChild(int i, BStarTreeNode y) {
        BStarTreeNode z = new BStarTreeNode(y.t, y.leaf);
        z.n = t - 1;
        for (int j = 0; j < t - 1; j++) z.keys[j] = y.keys[j + t];
        if (!y.leaf) for (int j = 0; j < t; j++) z.children[j] = y.children[j + t];
        y.n = t - 1;

        for (int j = n; j >= i + 1; j--) children[j + 1] = children[j];
        children[i + 1] = z;
        for (int j = n - 1; j >= i; j--) keys[j + 1] = keys[j];
        keys[i] = y.keys[t - 1];
        n++;
    }

    // Метод удаления (упрощённая версия)
    public void remove(int k, int[] counter) {
        int idx = 0;
        while (idx < n && keys[idx] < k) {
            idx++;
            counter[0]++;
        }
        if (idx < n && keys[idx] == k) {
            if (leaf) {
                for (int i = idx + 1; i < n; ++i) keys[i - 1] = keys[i];
                n--;
            } else {
                // не полная реализация удаления из внутреннего узла
                keys[idx] = keys[n - 1];
                n--;
            }
        } else {
            if (!leaf) children[idx].remove(k, counter);
        }
    }
}

