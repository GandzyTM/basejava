package ru.javawebinar.basejava.exception;

import java.sql.SQLException;

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String msg) {
        this(msg, null, null);
    }

    public StorageException(String msg, Exception e) {
        this(msg, null, e);
    }

    public StorageException(String msg, String uuid) {
        super(msg);
        this.uuid = uuid;
    }

    public StorageException(String msg, String uuid, Exception e) {
        super(msg, e);
        this.uuid = uuid;
    }

    public StorageException(Exception e) {
        this(e.getMessage(), e);
    }

    public String getUuid() {
        return uuid;
    }
}
