package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.SortedArrayStorage;
import ru.javawebinar.basejava.storage.Storage;

import java.util.Arrays;

/**
 * Test for your ru.javawebinar.basejava.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new ArrayStorage();

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

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r9);
        ARRAY_STORAGE.save(r6);
        ARRAY_STORAGE.save(r8);
        ARRAY_STORAGE.save(r7);
        ARRAY_STORAGE.save(r10);

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
