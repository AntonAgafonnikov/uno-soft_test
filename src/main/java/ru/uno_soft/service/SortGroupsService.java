package ru.uno_soft.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static ru.uno_soft.service.ProcessLineService.groupMergeHashMap;

public class SortGroupsService {
    public static HashMap<Integer, ArrayList<Integer>> sortedGroupMergeHashMap;

    public static void sortGroups() {
        sortedGroupMergeHashMap = new HashMap<>(groupMergeHashMap);
        System.out.println("Сортировка групп...");
        for (Map.Entry<Integer, ArrayList<Integer>> entry : groupMergeHashMap.entrySet()) {
            ArrayList<Integer> newValueSet = mergeGroups(entry.getValue());
            if (!entry.getValue().equals(newValueSet))
                sortedGroupMergeHashMap.put(entry.getKey(), newValueSet);
        }
        System.out.println("Сортировка групп завершена");
    }

    private static ArrayList<Integer> mergeGroups(ArrayList<Integer> curValueSet) {
        ArrayList<Integer> resultValueSet = new ArrayList<>(curValueSet);
        for (Integer value : curValueSet) {
            if (sortedGroupMergeHashMap.containsKey(value)) {
                ArrayList<Integer> newValueSet = sortedGroupMergeHashMap.get(value);
                resultValueSet.addAll(newValueSet);
                sortedGroupMergeHashMap.remove(value);
                mergeGroups(newValueSet);
            }
        }
        return resultValueSet;
    }
}
