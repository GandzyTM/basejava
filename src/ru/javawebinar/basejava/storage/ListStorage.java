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
        return  searchKey != null;
    }

    @Override
    //https://stackoverflow.com/questions/25676911/how-to-find-an-element-in-an-arraylist-by-using-a-field-value-of-that-element?rq=1
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void doDelete(Object searchKey) {
        int idx = (int) searchKey;
        list.remove(idx);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        list.set((Integer) searchKey, resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return list.get((Integer) searchKey);
    }

    @Override
    protected List<Resume> getAllElements() {
        return new ArrayList<>(list);
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