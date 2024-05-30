package ru.uno_soft.repository;

import java.util.HashMap;

public class ValueRepository {
    private static HashMap<String, Integer> valueGroupHashMap;

    private ValueRepository() {
    }

    public static synchronized HashMap<String, Integer> getValueGroupHashMap() {
        if (valueGroupHashMap == null) {
            valueGroupHashMap = new HashMap<>();
        }
        return valueGroupHashMap;
    }
}
