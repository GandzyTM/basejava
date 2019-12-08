package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.*;

public class MainTestList {
    private static final Storage listStorage = new ArrayStorage();

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
        Resume r6 = new Resume("uuid6");
        r6.setUuid("uuid6");
        Resume r7 = new Resume("uuid7");
        r7.setUuid("uuid7");
        Resume r8 = new Resume("uuid8");
        r8.setUuid("uuid8");
        Resume r9 = new Resume("uuid9");
        r9.setUuid("uuid9");
        Resume r10 = new Resume("uuid10");
        r10.setUuid("uuid10");
        Resume r11 = new Resume("uuid10");
        r11.setUuid("uuid11");
        Resume r12 = new Resume("new resume");

        listStorage.save(r1);
        listStorage.save(r2);
        listStorage.save(r3);
        listStorage.save(r4);
        listStorage.save(r5);
        listStorage.save(r6);
        listStorage.save(r7);
        listStorage.save(r8);
        listStorage.save(r9);
        listStorage.save(r10);
        listStorage.save(r11);
        listStorage.save(r12);

        System.out.println("Get r3: " + listStorage.get(r3.getUuid()));
        System.out.println("Get r11: " + r11.getUuid());
        System.out.println("Get r11: " + listStorage.get(r11.getUuid()));
        System.out.println("Size: " + listStorage.size());

//        System.out.println("Get dummy: " + listStorage.get("dummy"));
        printAll();
        listStorage.delete(r3.getUuid());
        printAll();
        listStorage.update(r6);
        System.out.println(r6 + " updated");
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
