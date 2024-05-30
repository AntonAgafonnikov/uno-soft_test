package ru.uno_soft;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;

import static ru.uno_soft.file.in.InBufferReader.groupMergeHashMap;
import static ru.uno_soft.file.in.InBufferReader.readFromFile;
import static ru.uno_soft.repository.GroupRepository.getGroupLinesHashMap;
import static ru.uno_soft.service.ProcessingLineService.*;

public class Main {
    public static void main(String[] args) {
        readFromFile(new File("src/main/resources/test.txt"));
        sortGroups();

        for (Map.Entry<Integer, TreeSet<Integer>> entry : groupMergeHashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("---------");

        for (Map.Entry<Integer, TreeSet<Integer>> entry : sortedGroupMergeHashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

//        System.out.println("---------");
//        for (Map.Entry<Integer, ArrayList<String>> entry : getGroupLinesHashMap().entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }

    }
}