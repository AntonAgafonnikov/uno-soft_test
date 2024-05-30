package ru.uno_soft.file.in;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

import static ru.uno_soft.repository.GroupRepository.getGroupLinesHashMap;
import static ru.uno_soft.repository.ValueRepository.getValueGroupHashMap;
import static ru.uno_soft.service.ProcessingLineService.processingLine;

public class InBufferReader {
    public static TreeMap<Integer, TreeSet<Integer>> groupMergeHashMap = new TreeMap<>(); //TODO private
    private static int group = 1;
    public static void readFromFile(File file) {


        try (
                var fileReader = new FileReader(file);
                var bufferedReader = new BufferedReader(fileReader)
        ) {
            System.out.println("Чтение строк из файла...");
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                var isCorrectValuesArray = line.replace(";", "")
                        .replace("\"", "");
                if (isCorrectValuesArray.isEmpty()) continue;

                var valueArray = line.split(";");
                int curGroup = 0;
                for (int i = 0; i < valueArray.length; i++) {
                    String valueCur = String.valueOf((valueArray[i] + i).hashCode());
                    if (valueCur.equals("\"\"")) continue;
                    if (getValueGroupHashMap().containsKey(valueCur) && curGroup == 0) {
                        curGroup = getValueGroupHashMap().get(valueCur);
                        i = 0;
                    }

                    if (curGroup != 0) {
                        TreeSet<Integer> newSet;
                        if (groupMergeHashMap.containsKey(curGroup)) {
                            newSet = groupMergeHashMap.get(curGroup);
                            newSet.add(group);
                        } else {
                            newSet = new TreeSet<>();
                            newSet.add(group);
                        }

                        if (getValueGroupHashMap().containsKey(valueCur)) {
                            if (curGroup != getValueGroupHashMap().get(valueCur)) {
                                newSet.add(getValueGroupHashMap().get(valueCur));
                            }
                        }
                        getValueGroupHashMap().put(valueCur, curGroup);
                        groupMergeHashMap.put(curGroup, newSet);
                    } else
                        getValueGroupHashMap().put(valueCur, group);
                }

                getGroupLinesHashMap().put(group, new ArrayList<>(Arrays.asList(valueArray)));
                group++;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Чтение строк завершено.");
    }
}
