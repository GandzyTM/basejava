package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListTextSection extends Section {
    private final List<String> list;

    public ListTextSection(List<String> list) {
        Objects.requireNonNull(list, "text section couldn't be null");
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListTextSection listText = (ListTextSection) o;

        return Objects.equals(list, listText.list);
    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ListText{" +
                "list=" + list +
                '}';
    }
}
