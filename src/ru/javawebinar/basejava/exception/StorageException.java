package ru.javawebinar.basejava.exception;

import java.io.IOException;

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String storage_is_full, String uuid) {
        super(storage_is_full);
        this.uuid = uuid;
    }

    public StorageException(String storage_is_full, String uuid, Exception e) {
        super(storage_is_full, e);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
