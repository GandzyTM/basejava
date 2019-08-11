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
        int fe = findElement(uuid);
        if (fe != -1) {
            return storage[fe];
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
            storage[k] = storage[k + 1];
        }
        incSize--;
    }

    void clear() {
        Arrays.fill(storage, 0, incSize, null);
        incSize = 0;
    }

    void update(String uuid) {

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
