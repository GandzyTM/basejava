import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int incSize = 0;

    void save(Resume r) {
        storage[incSize] = r;
        incSize++;
    }

    Resume get(String uuid) {
        int index = findElement(uuid);
        if (index != -1) {
            return storage[index];
        }
        return null;
    }

    int size() {
        return incSize;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, incSize);
//        return new Resume[0];
    }

    void delete(String uuid) {
        for (int k = findElement(uuid); k < incSize - 1; k++) {
            if (storage[k].getUuid().equals(uuid)) {
                storage[k] = storage[k + 1];
                incSize--;
            } else {
                System.out.println(uuid + " didn't in Resume storage");
            }
        }
    }

    void clear() {
        Arrays.fill(storage, 0, incSize, null);
        incSize = 0;
    }

    void update(Resume resume) {
        // delete
        // insert or save

    }

    private int findElement(String uuid) {
        for (int i = 0; i < incSize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
