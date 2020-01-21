package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serialize.DataStreamSerializer;

public class DataStorageTest extends AbstractStorageTest {

    public DataStorageTest() {
        super(new PathStorage(STORAGE_DIR.getPath(), new DataStreamSerializer()));
    }
}
