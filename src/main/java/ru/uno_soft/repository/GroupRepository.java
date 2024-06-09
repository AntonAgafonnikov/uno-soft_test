package ru.uno_soft.repository;

import java.util.ArrayList;

public class GroupRepository {
    private static ArrayList<String> groupLinesHashMap;

    private GroupRepository() {
    }

    public static synchronized ArrayList<String> getGroupLinesHashMap() {
        if (groupLinesHashMap == null) {
            groupLinesHashMap = new ArrayList<>();
        }
        return groupLinesHashMap;
    }
}
