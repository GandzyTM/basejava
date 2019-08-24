package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SortedArrayStorage;
import ru.javawebinar.basejava.storage.Storage;

/**
 * Test for your ru.javawebinar.basejava.storage.ArrayStorage implementation
 */
public class MainTestSortedArrayStorage {
    static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
//        r1.setUuid("uuid1");
        Resume r2 = new Resume("uuid2");
//        r2.setUuid("uuid2");
        Resume r3 = new Resume("uuid3");
//        r3.setUuid("uuid3");
        Resume r4 = new Resume("uuid4");
//        r4.setUuid("uuid4");
        Resume r5 = new Resume("uuid4");
//        r5.setUuid("uuid4");
        Resume r6 = new Resume("uuid6");
//        r6.setUuid("uuid6");
        Resume r7 = new Resume("uuid7");
//        r7.setUuid("uuid7");
        Resume r8 = new Resume("uuid8");
//        r8.setUuid("uuid8");
        Resume r9 = new Resume("uuid9");
//        r9.setUuid("uuid9");
        Resume r10 = new Resume("uuid10");
//        r10.setUuid("uuid10");
        Resume r11 = new Resume("uuid11");
//        r11.setUuid("uuid11");

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
        ARRAY_STORAGE.save(r11); // 10
// testing
//        System.out.println("Index of r6: " + Arrays.binarySearch(ARRAY_STORAGE.storage, 0, ARRAY_STORAGE.size(), r6));
//        System.out.println("Index of r7: " + Arrays.binarySearch(ARRAY_STORAGE.storage, 0, ARRAY_STORAGE.size(), r7));
//        System.out.println("Index of r9: " + Arrays.binarySearch(ARRAY_STORAGE.storage, 0, ARRAY_STORAGE.size(), r9));
//        System.out.println("Index of r10: " + Arrays.binarySearch(ARRAY_STORAGE.storage, 0, ARRAY_STORAGE.size(), r10));
//        System.out.println("Index of r11: " + Arrays.binarySearch(ARRAY_STORAGE.storage, 0, ARRAY_STORAGE.size(), r11));
//        System.out.println(r5.compareTo(r11));
// testing end

        System.out.println("Get r3: " + ARRAY_STORAGE.get(r3.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
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
