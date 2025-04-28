package org.example;

public class CombSorter {

    private int iterationCount = 0;

    public int getIterationCount() {
        return iterationCount;
    }

    public void sort(int[] array) {
        int n = array.length;
        int gap = n;
        boolean swapped = true;
        iterationCount = 0;

        while (gap != 1 || swapped) {
            gap = getNextGap(gap);
            swapped = false;

            for (int i = 0; i < n - gap; i++) {
                iterationCount++;
                if (array[i] > array[i + gap]) {
                    int temp = array[i];
                    array[i] = array[i + gap];
                    array[i + gap] = temp;
                    swapped = true;
                }
            }
        }
    }

    private int getNextGap(int gap) {
        gap = (gap * 10) / 13;
        if (gap < 1) {
            return 1;
        }
        return gap;
    }
}
