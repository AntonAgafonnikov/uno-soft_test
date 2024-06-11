package ru.uno_soft.file.out;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import static ru.uno_soft.repository.GroupRepository.getLinesList;
import static ru.uno_soft.service.SortGroupsService.sortedGroupMergeHashMap;

public class OutBufferWriter {

    public static void writeToFile(File file) {
        System.out.println("Запись результатов в файл...");
        var result = "Число групп с более чем одним элементом: " + sortedGroupMergeHashMap.size();

        //Сортируем по убыванию
        SortedSet<Map.Entry<Integer, ArrayList<Integer>>> sortedset = new TreeSet<>(
                (list1, list2) -> {
                    if (list1.getValue().size() < list2.getValue().size()) return 1;
                    else if (list1.getValue().size() > list2.getValue().size()) return -1;
                    else return 1;
                }
        );
        sortedset.addAll(sortedGroupMergeHashMap.entrySet());

        try (
                var fileWriter = new FileWriter(file);
                var bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            bufferedWriter.write(result + "\n");

            //Записываем в файл отсортированные группы, у котрых более 1 элемента
            int countGroup = 1;
            for (Map.Entry<Integer, ArrayList<Integer>> entry : sortedset) {
                var key = entry.getKey();
                var valueList = entry.getValue();
                bufferedWriter.write("\nГруппа №" + countGroup + ":\n");
                bufferedWriter.write(getLinesList().get(key) + "\n");
                for (Integer group : valueList) {
                    bufferedWriter.write(getLinesList().get(group) + "\n");
                }
                countGroup++;
            }

            //Записываем в файл оставшиеся группы
            for (int i = 0; i < getLinesList().size(); i++) {
                if (sortedGroupMergeHashMap.containsKey(i)) continue;
                bufferedWriter.write("\nГруппа №" + countGroup + ":\n");
                bufferedWriter.write(getLinesList().get(i) + "\n");
                countGroup++;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nЗапись завершена.\n" + result);
    }
}
