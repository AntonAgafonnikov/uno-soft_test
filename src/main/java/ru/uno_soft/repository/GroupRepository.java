package ru.uno_soft.repository;

import java.util.ArrayList;
import java.util.TreeMap;

public class GroupRepository {
    private static TreeMap<Integer, ArrayList<String>> groupLinesHashMap;

    private GroupRepository() {
    }

    public static synchronized TreeMap<Integer, ArrayList<String>> getGroupLinesHashMap() {
        if (groupLinesHashMap == null) {
            groupLinesHashMap = new TreeMap<>();
        }
        return groupLinesHashMap;
    }
}
