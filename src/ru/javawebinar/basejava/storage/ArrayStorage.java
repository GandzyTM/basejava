package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveElement(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void deleteElement(int index) {
        storage[index] = storage[size];
    }

    @Override
    protected int getSearchIndexKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
