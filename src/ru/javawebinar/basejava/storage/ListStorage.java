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
    // https://stackoverflow.com/questions/2131802/java-arraylist-how-can-i-check-if-an-index-exists
    protected boolean isExist(Object searchKey) {
        if ((Integer) searchKey < list.size()) {
            return true;
        }
        return false;
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

