package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        list.add(resume);
    }

    @Override
    protected boolean isExist(Object searchKey) {

    }

    @Override
    protected int getSearchIndexKey(String uuid) {
        return list.indexOf(uuid);
    }

    @Override
    protected void doDelete(Object searchKey) {
        list.remove(searchKey);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return list.get((Integer) searchKey);
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }
}
