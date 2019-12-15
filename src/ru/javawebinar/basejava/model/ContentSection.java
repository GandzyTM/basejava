package ru.javawebinar.basejava.model;

import java.util.Objects;

public class ContentSection extends Section {
    private final String content;

    public ContentSection(String content) {
        Objects.requireNonNull(content, "content couldn't be null");
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentSection contentSection = (ContentSection) o;

        return content.equals(contentSection.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return "ContentSection{" +
                "content='" + content + '\'' +
                '}';
    }
}
