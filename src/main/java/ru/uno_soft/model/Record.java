package ru.uno_soft.model;

import java.util.Objects;

public class Record {
    private String value;
    private int index;

    public Record(String value, int index) {
        this.value = value;
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return index == record.index && Objects.equals(value, record.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, index);
    }
}
