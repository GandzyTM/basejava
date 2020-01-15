package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListTextSection extends Section {
    private final List<String> items;

    public ListTextSection(String... items) {
        this(Arrays.asList(items));
    }

    public ListTextSection(List<String> items) {
        Objects.requireNonNull(items, "text section couldn't be null");
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListTextSection listText = (ListTextSection) o;

        return Objects.equals(items, listText.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        return "ListText{" +
                "list=" + items +
                '}';
    }
}

