package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.SortedArrayStorage;
import ru.javawebinar.basejava.storage.Storage;

import java.util.Arrays;

/**
 * Test for your ru.javawebinar.basejava.storage.ArrayStorage implementation
 */
public class MainTestSortedArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");
        Resume r4 = new Resume();
        r4.setUuid("uuid4");
        Resume r5 = new Resume();
        r5.setUuid("uuid5");
        Resume r6 = new Resume();
        r6.setUuid("uuid6");
        Resume r7 = new Resume();
        r7.setUuid("uuid7");
        Resume r8 = new Resume();
        r8.setUuid("uuid8");
        Resume r9 = new Resume();
        r9.setUuid("uuid9");
        Resume r10 = new Resume();
        r10.setUuid("uuid10");

        ARRAY_STORAGE.save(r1); // 0
        ARRAY_STORAGE.save(r2); // 1
        ARRAY_STORAGE.save(r3); // 2
        ARRAY_STORAGE.save(r4); // 3
        ARRAY_STORAGE.save(r5); // 4
        ARRAY_STORAGE.save(r6); // 5
        ARRAY_STORAGE.save(r7); // 6
        ARRAY_STORAGE.save(r8); // 7
        ARRAY_STORAGE.save(r9); // 8
        ARRAY_STORAGE.save(r10); // 9

        System.out.println("Get r3: " + ARRAY_STORAGE.get(r3.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
// testing
        System.out.println("Index of r9: " + Arrays.binarySearch(ARRAY_STORAGE.storage, 0, ARRAY_STORAGE.size(), r9));
// testing end
        printAll();
        ARRAY_STORAGE.delete(r3.getUuid());
        printAll();
        ARRAY_STORAGE.update(r5);
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
