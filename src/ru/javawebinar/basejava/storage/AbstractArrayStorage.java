package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected void doSave(Resume resume, Object index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage is full", resume.getUuid());
        } else {
            saveElement(resume, (Integer) index);
            size++;
        }
    }

    protected abstract void saveElement(Resume resume, int index);

    @Override
    protected void doDelete(Object index) {
        deleteElement((Integer) index);
        size--;
        storage[size] = null;
    }

    protected abstract void deleteElement(int index);

    @Override
    protected void doUpdate(Resume resume, Object index) {
        storage[(int) index] = resume;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(int) index];
    }

    protected abstract int getSearchIndexKey(String uuid);

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }
}
