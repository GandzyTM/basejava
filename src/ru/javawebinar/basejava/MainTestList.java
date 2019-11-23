package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ListStorage;
import ru.javawebinar.basejava.storage.Storage;

public class MainTestList {
    private static final Storage listStorage = new ListStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        r1.setUuid("uuid1");
        Resume r2 = new Resume("uuid2");
        r2.setUuid("uuid2");
        Resume r3 = new Resume("uuid3");
        r3.setUuid("uuid3");
        Resume r4 = new Resume("uuid4");
        r4.setUuid("uuid4");
        Resume r5 = new Resume("uuid5");
        r5.setUuid("uuid5");

        listStorage.save(r1);
        listStorage.save(r2);
        listStorage.save(r3);
        listStorage.save(r4);
        listStorage.save(r5);

        System.out.println("Get r3: " + listStorage.get(r3.getUuid()));
        System.out.println("Size: " + listStorage.size());

        System.out.println("Get dummy: " + listStorage.get("dummy"));
        printAll();
        listStorage.delete(r3.getUuid());
        printAll();
        listStorage.update(r5);
        printAll();
        listStorage.clear();
        printAll();
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : listStorage.getAllSorted()) {
            System.out.println(r);
        }
    }
}
