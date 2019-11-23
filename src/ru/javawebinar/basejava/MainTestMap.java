package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.MapStorage;
import ru.javawebinar.basejava.storage.Storage;

public class MainTestMap {
    private static final Storage mapStorage = new MapStorage();

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

        mapStorage.save(r1);
        mapStorage.save(r2);
        mapStorage.save(r3);
        mapStorage.save(r4);
        mapStorage.save(r5);

        System.out.println("Get r3: " + mapStorage.get(r3.getUuid()));
        System.out.println("Size: " + mapStorage.size());

//        System.out.println("Get dummy: " + listStorage.get("dummy"));
        printAll();
        mapStorage.delete(r3.getUuid());
        printAll();
        mapStorage.update(r5);
        printAll();
        mapStorage.clear();
        printAll();
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : mapStorage.getAllSorted()) {
            System.out.println(r);
        }
    }
}
