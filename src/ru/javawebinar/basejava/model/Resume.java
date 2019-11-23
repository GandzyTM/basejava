package ru.javawebinar.basejava.model;

import java.util.UUID;

public class Resume implements Comparable<Resume> {
    private String uuid;
    private String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume o) {
//https://stackoverflow.com/questions/369512/how-to-compare-objects-by-multiple-fields
        int siezCmp = fullName.compareTo(o.fullName);
        if (siezCmp != 0) {
            return siezCmp;
        } else {
            return this.uuid.compareTo(o.uuid);
        }
    }
}