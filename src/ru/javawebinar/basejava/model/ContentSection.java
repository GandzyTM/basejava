package ru.javawebinar.basejava.model;

public class ContentSection extends Section {
    private final String content;


    public ContentSection(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentSection content1 = (ContentSection) o;

        return content.equals(content1.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        return "Content{" +
                "content='" + content + '\'' +
                '}';
    }
}
