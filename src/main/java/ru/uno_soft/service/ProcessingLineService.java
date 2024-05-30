package ru.uno_soft.service;

import java.util.*;

import static ru.uno_soft.file.in.InBufferReader.groupMergeHashMap;

public class ProcessingLineService {

    public static TreeMap <Integer, TreeSet<Integer>> sortedGroupMergeHashMap = new TreeMap<>(); //TODO private
    private static HashMap<Integer, Integer> mergedGroupsHashMap = new HashMap<>();

    public static void processingLine() {

    }

    public static void sortGroups() {
        System.out.println("Сортировка групп...");
        for (Map.Entry<Integer, TreeSet<Integer>> entry : groupMergeHashMap.entrySet()) {
            int curKey = entry.getKey();
            TreeSet<Integer> curValueSet = entry.getValue();
            TreeSet<Integer> newValueSet = new TreeSet<>(curValueSet);

            for (Integer value : curValueSet) {
                if(groupMergeHashMap.containsKey(value)) {
                    if (mergedGroupsHashMap.containsKey(value)){
                        int groupMerge = mergedGroupsHashMap.get(value);
                        if (sortedGroupMergeHashMap.containsKey(groupMerge)) {
                            TreeSet<Integer> mergeSet = sortedGroupMergeHashMap.get(groupMerge);
                            mergeSet.add(curKey);
                            mergeSet.addAll(curValueSet);
                            sortedGroupMergeHashMap.put(groupMerge, mergeSet);
                            mergedGroupsHashMap.put(curKey, groupMerge);
                        } else {
                            TreeSet<Integer> newSet = new TreeSet<>();
                            newSet.add(curKey);
                            sortedGroupMergeHashMap.put(groupMerge, newSet);
                        }
                    } else {
                        newValueSet.addAll(groupMergeHashMap.get(value));
                        mergedGroupsHashMap.put(value, curKey);
                        sortedGroupMergeHashMap.put(value, newValueSet);
                    }
                }
            }
        }

        System.out.println("Сортировка групп завершена");
    }

}
