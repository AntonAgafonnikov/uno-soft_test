package ru.uno_soft.file.out;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static ru.uno_soft.repository.GroupRepository.getGroupLinesHashMap;
import static ru.uno_soft.service.SortGroupsService.sortedGroupMergeHashMap;

public class OutBufferWriter {

    public static void writeToFile(File file) {
        System.out.println("Запись результатов в файл...");


        var maxSizeList = findMaxSize();
        var count = 1;
        var result = "Число групп с более чем одним элементом: " + sortedGroupMergeHashMap.size();

        try (
                var fileWriter = new FileWriter(file);
                var bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            bufferedWriter.write(result);

            while (maxSizeList > 0) {

                for (Map.Entry<Integer, ArrayList<Integer>> entry : sortedGroupMergeHashMap.entrySet()) {

                    if (entry.getValue().size() == maxSizeList) {
                        bufferedWriter.write("\nГруппа " + count + "\n");

                        for (Integer group : entry.getValue()) {
                            bufferedWriter.write(getGroupLinesHashMap().get(group) + "\n");
                        }
                        count++;
                    }
                }
                maxSizeList--;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nЗапись завершена.\n" + result);
    }

    private static int findMaxSize() {
        var maxSize = 0;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : sortedGroupMergeHashMap.entrySet()) {
            var currentValueSize = entry.getValue().size();
            if (currentValueSize > maxSize) {
                maxSize = currentValueSize;
            }
        }
        return maxSize;
    }
}
