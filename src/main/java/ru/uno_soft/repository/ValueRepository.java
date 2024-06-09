package ru.uno_soft.repository;

import ru.uno_soft.model.Record;

import java.util.HashMap;

public class ValueRepository {
    private static HashMap<Record, Integer> valueGroupHashMap;

    private ValueRepository() {
    }

    public static synchronized HashMap<Record, Integer> getValueGroupHashMap() {
        if (valueGroupHashMap == null) {
            valueGroupHashMap = new HashMap<>();
        }
        return valueGroupHashMap;
    }
}
