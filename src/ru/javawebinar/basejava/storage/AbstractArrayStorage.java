package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Can't save " + resume.getUuid() + " because resume storage is full");
        } else {
            int index = findIndex(resume.getUuid());
            if (index < 0) {
                saveElement(resume, index); // changed code for child classes
                //storage[size] = resume;
                size++;
            } else {
                System.out.println("Resume " + resume.getUuid() + " already exists in storage");
            }
        }
    }

    protected abstract void saveElement(Resume resume, int index);

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index > 0) {
            size--;
            deleteElement(index); // changed code for child classes
            //storage[index] = storage[size];
            storage[size] = null;
        } else {
            System.out.println("Resume " + uuid + " not exist in storage");
        }
    }

    protected abstract void deleteElement(int index);

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
            System.out.println("Resume " + resume.getUuid() + " updated");
        } else {
            System.out.println("Resume " + resume.getUuid() + " not exist in storage");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " not exist in storage");
        return null;
    }

    protected abstract int findIndex(String uuid);

}
