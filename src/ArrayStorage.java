import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    protected void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("Can't save " + resume.getUuid() + " because resume storage is full");
        } else if (findIndex(resume.getUuid()) == -1) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Resume " + resume.getUuid() + " already exists in storage");
        }
    }

    protected Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " not exist in storage");
        return null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    protected Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected void delete(String uuid) {
        int index = findIndex(uuid);
        size--;
        if (index != -1) {
            storage[index] = storage[size];
            storage[size] = null;
        } else {
            System.out.println("Resume " + uuid + " not exist in storage");
        }
    }

    protected void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " not exist in storage");
        }
    }

    protected void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
