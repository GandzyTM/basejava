import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void save(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                storage[size] = r;
                size++;
            } else {
                System.out.println(r.getUuid() + " is in storage");
            }
        }
    }

    Resume get(String uuid) {
        int index = findElement(uuid);
        if (index != -1) {
            return storage[index];
        }
        return null;
    }

    int size() {
        return size;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
//        return new Resume[0];
    }

    void delete(String uuid) {
        for (int i = findElement(uuid); i < size - 1; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[i + 1];
                size--;
            } else {
                System.out.println(uuid + " didn't in Resume storage");
            }
        }
    }

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void update(Resume resume) {
        // delete
        delete(resume.getUuid());
        // insert or save
        for (int i = 0; i < size; i++) {

        }

    }

    private int findElement(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
