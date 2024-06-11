package ru.uno_soft.service;

import ru.uno_soft.model.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ru.uno_soft.repository.GroupRepository.getLinesList;

public class ProcessLineService {

    public static HashMap<Integer, ArrayList<Integer>> groupMergeHashMap = new HashMap<>();
    private static HashMap<Record, Integer> valueGroupHashMap = new HashMap<>();;
    private static int group = 0;

    public static void processLine(List<String> lines) {
        for (String line : lines) {
            var valueArray = line.split(";");
            int curGroup = 0;
            for (int i = 0; i < valueArray.length; i++) {

                if (valueArray[i].isEmpty() || valueArray[i].equals("\"\"")
                        || valueArray[i].substring(1, valueArray[i].length() - 1).contains("\"")) continue;

                Record valueCur = new Record(valueArray[i], i);
                boolean isRecordContainsInList = valueGroupHashMap.containsKey(valueCur);
                if (isRecordContainsInList && curGroup == 0) {
                    curGroup = valueGroupHashMap.get(valueCur);
                    i = 0;
                }

                if (curGroup != 0) {
                    ArrayList<Integer> newList;
                    if (groupMergeHashMap.containsKey(curGroup)) {
                        newList = groupMergeHashMap.get(curGroup);
                        if (!newList.contains(group))
                            newList.add(group);
                    } else {
                        newList = new ArrayList<>();
                        newList.add(group);
                    }

                    if (isRecordContainsInList && curGroup != valueGroupHashMap.get(valueCur)
                            && !newList.contains(valueGroupHashMap.get(valueCur)))
                        newList.add(valueGroupHashMap.get(valueCur));

                    valueGroupHashMap.put(valueCur, curGroup);
                    groupMergeHashMap.put(curGroup, newList);
                } else {
                    valueGroupHashMap.put(valueCur, group);
                }
            }
            getLinesList().add(line);
            group++;
        }
    }
}

