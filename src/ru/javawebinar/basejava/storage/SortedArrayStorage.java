package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveElement(Resume resume, int index) {
        int insertIdx = -index - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
        storage[insertIdx] = resume;
    }

    @Override
    protected void deleteElement(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            for (int i = numMoved; i < index + 1; i++) {
                storage[i] = storage[i + 1];
            }
        }
    }

    @Override
    protected Integer getSearchIndexKey(String uuid) {
        Comparator<Resume> resumeComparator = Comparator.naturalOrder();
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey, resumeComparator);
    }
}
