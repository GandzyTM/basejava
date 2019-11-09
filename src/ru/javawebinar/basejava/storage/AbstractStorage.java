package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        Object searchKey = getSearchIndexKey(resume.getUuid());
        if (!isExist(searchKey)) {
            doSave(resume, searchKey);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        Object searchKey = getSearchIndexKey(uuid);
        if (isExist(searchKey)) {
            doDelete(searchKey);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void update(Resume resume) {
        Object searchKey = getSearchIndexKey(resume.getUuid());
        if (isExist(searchKey)) {
            doUpdate(resume, searchKey);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public Resume get(String uuid) {
        Object searchKey = getSearchIndexKey(uuid);
        if (isExist(searchKey)) {
            return doGet(searchKey);
        } else {
            throw new NotExistStorageException(uuid);
        }

    }

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract Object getSearchIndexKey(String uuid);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract Resume doGet(Object searchKey);
}
