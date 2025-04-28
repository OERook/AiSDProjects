package org.example;

import java.io.IOException;
import java.util.List;

public class Main {

    private static final int REPEAT_COUNT = 5; // сколько раз замерять

    public static void main(String[] args) {
        String filename = "testdata.txt";

        try {
            // Генерация тестовых данных
            DataUtils.generateTestData(filename, 50, 100, 10000);

            // Чтение тестовых данных
            List<int[]> datasets = DataUtils.readTestData(filename);

            for (int i = 0; i < datasets.size(); i++) {
                int[] originalData = datasets.get(i);

                long totalNanoTime = 0;
                int iterationCount = 0;

                for (int j = 0; j < REPEAT_COUNT; j++) {
                    int[] data = originalData.clone(); // Копия для каждого замера
                    CombSorter sorter = new CombSorter();

                    long startTime = System.nanoTime();
                    sorter.sort(data);
                    long endTime = System.nanoTime();

                    totalNanoTime += (endTime - startTime);
                    iterationCount = sorter.getIterationCount(); // берем итерации с последнего прогона
                }

                long averageNanoTime = totalNanoTime / REPEAT_COUNT;
                double averageMillisTime = averageNanoTime / 1_000_000.0;

                System.out.println("Набор #" + (i + 1));
                System.out.println("Размер массива: " + originalData.length);
                System.out.println("Количество итераций: " + iterationCount);
                System.out.printf("Среднее время выполнения: %.3f мс (%d нс)%n", averageMillisTime, averageNanoTime);
                System.out.println("------------------------------------");
            }

        } catch (IOException e) {
            System.err.println("Ошибка работы с файлом: " + e.getMessage());
        }
    }
}
