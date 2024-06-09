package ru.uno_soft.model;

import java.util.Objects;

public class Record {
    private String value;
    private int index;
    private int group;

    public Record(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
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
