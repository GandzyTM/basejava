import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void save(Resume r) {
        int index = findElement(r.getUuid());
        if (index == -1 || size > storage.length) {
            storage[size] = r;
            size++;
        } else {
            System.out.println(r.getUuid() + " is in storage");
        }
    }

    Resume get(String uuid) {
        int index = findElement(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println(uuid + " didn't in Resume storage");
        }
        return null;
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
                int var3 = size - findElement(uuid) - 1;
                System.arraycopy(storage, i + 1, storage, findElement(uuid), var3);
                size--;
            } else {
                System.out.println(uuid + " didn't in Resume storage");
            }
        }
    }

    void update(Resume resume) {
        // delete
        // insert or save

    }

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    int size() {
        return size;
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
