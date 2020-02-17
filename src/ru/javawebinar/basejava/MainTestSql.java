package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

public class MainTestSql {
    private static final Storage sqlStorage = new SqlStorage(Config.getInstance().getUrl(), Config.getInstance().getUser(), Config.getInstance().getPassword());

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

        sqlStorage.save(r1);
        sqlStorage.save(r2);
        sqlStorage.save(r3);
        sqlStorage.save(r4);
        sqlStorage.save(r5);
        sqlStorage.save(r6);
        sqlStorage.save(r7);
        sqlStorage.save(r8);
        sqlStorage.save(r9);
        sqlStorage.save(r10);
        sqlStorage.save(r11);
        sqlStorage.save(r12);

        System.out.println("Get r3: " + sqlStorage.get(r3.getUuid()));
        System.out.println("Get r11: " + sqlStorage.get(r11.getUuid()));
//        System.out.println("Size: " + sqlStorage.size());

//        System.out.println("Get dummy: " + listStorage.get("dummy"));
        printAll();
        sqlStorage.delete(r4.getUuid());
        sqlStorage.delete(r4.getUuid());
        printAll();
        sqlStorage.update(r6);
        System.out.println(r6 + " updated");
        printAll();
        sqlStorage.clear();
        printAll();
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : sqlStorage.getAllSorted()) {
            System.out.println(r);
        }
    }
}
