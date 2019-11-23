package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public interface Storage {

    void save(Resume resume);

    Resume get(String uuid);

    List<Resume> getAllSorted();

    void delete(String uuid);

    void update(Resume resume);

    void clear();

    int size();
}