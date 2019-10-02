package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static ru.javawebinar.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractStorage implements Storage {
    public void delete(String uuid) {
        int searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            doDelete();
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract int getSearchKey(String uuid);

    protected abstract void doDelete();

    public void update(Resume resume) {
        Object searchKey = getSearchKey(resume.getUuid());
        if (isExist(searchKey)) {
            doUpdate(resume, searchKey);
            System.out.println("Resume " + resume.getUuid() + " updated");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    public void save(Resume resume) {
        Object searchKey = getSearchKey(resume.getUuid());
        if (!isExist(searchKey)) {
            doSaveElement(resume, searchKey);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    protected abstract void doSaveElement(Resume resume, Object searchKey);
}



