package org.example;

import java.io.*;
import java.util.*;

public class DataUtils {

    public static void generateTestData(String filename, int setCount, int minSize, int maxSize) throws IOException {
        Random random = new Random();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        for (int i = 0; i < setCount; i++) {
            int size = random.nextInt(maxSize - minSize + 1) + minSize;
            for (int j = 0; j < size; j++) {
                writer.write(random.nextInt(10000) + " ");
            }
            writer.newLine();
        }
        writer.close();
    }

    public static List<int[]> readTestData(String filename) throws IOException {
        List<int[]> datasets = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            int[] array = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                array[i] = Integer.parseInt(parts[i]);
            }
            datasets.add(array);
        }
        reader.close();
        return datasets;
    }
}
