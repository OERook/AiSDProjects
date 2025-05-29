package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] data = new int[10000];
        for (int i = 0; i < data.length; i++) {
            data[i] = rand.nextInt(100000);
        }

        BStarTree tree = new BStarTree(3);

        // Массивы для логирования
        long[] insertTimes = new long[10000];
        int[] insertOps = new int[10000];

        long[] searchTimes = new long[100];
        int[] searchOps = new int[100];

        long[] deleteTimes = new long[1000];
        int[] deleteOps = new int[1000];

        // ВСТАВКА
        for (int i = 0; i < data.length; i++) {
            int[] counter = new int[1];
            long start = System.nanoTime();
            tree.insert(data[i], counter);
            long end = System.nanoTime();
            insertTimes[i] = end - start;
            insertOps[i] = counter[0];
        }

        // ПОИСК
        for (int i = 0; i < 100; i++) {
            int val = data[rand.nextInt(data.length)];
            int[] counter = new int[1];
            long start = System.nanoTime();
            tree.search(val, counter);
            long end = System.nanoTime();
            searchTimes[i] = end - start;
            searchOps[i] = counter[0];
        }

        // УДАЛЕНИЕ
        for (int i = 0; i < 1000; i++) {
            int val = data[rand.nextInt(data.length)];
            int[] counter = new int[1];
            long start = System.nanoTime();
            tree.remove(val, counter);
            long end = System.nanoTime();
            deleteTimes[i] = end - start;
            deleteOps[i] = counter[0];
        }

        // Подсчёт среднего
        System.out.println("==== СТАТИСТИКА ====");
        printStats("Вставка", insertTimes, insertOps);
        printStats("Поиск", searchTimes, searchOps);
        printStats("Удаление", deleteTimes, deleteOps);
    }

    private static void printStats(String label, long[] times, int[] ops) {
        long totalTime = 0;
        int totalOps = 0;
        for (int i = 0; i < times.length; i++) {
            totalTime += times[i];
            totalOps += ops[i];
        }
        double avgTime = (double) totalTime / times.length;
        double avgOps = (double) totalOps / ops.length;
        System.out.printf("%s:\n  Среднее время: %.2f нс\n  Среднее операций: %.2f\n\n",
                label, avgTime, avgOps);
    }
}
