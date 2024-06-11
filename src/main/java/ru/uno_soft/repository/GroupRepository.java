package ru.uno_soft.repository;

import java.util.ArrayList;

public class GroupRepository {
    private static ArrayList<String> linesList;

    private GroupRepository() {
    }

    public static synchronized ArrayList<String> getLinesList() {
        if (linesList == null) {
            linesList = new ArrayList<>();
        }
        return linesList;
    }
}
