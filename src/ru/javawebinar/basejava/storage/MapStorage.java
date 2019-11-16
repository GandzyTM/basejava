package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<Object, Resume> map = new HashMap<>();

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        map.put(searchKey, resume);
    }

    @Override
    protected boolean isExist(Object uuid) {
        return map.containsKey(uuid);
    }

    @Override
    protected String getSearchKey(String searchKey) {
        return searchKey;
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(searchKey);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        map.replace(searchKey, resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(searchKey);
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[0]);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }
}
