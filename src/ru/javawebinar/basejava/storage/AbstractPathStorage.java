package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected abstract void doWrite(Resume resume, OutputStream outputStream) throws IOException;

    protected abstract Resume doRead(InputStream inputStream) throws IOException;

    protected AbstractPathStorage(String dir) {
        Path directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {

    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected void doSave(Resume resume, Path path) {

    }

    @Override
    protected Resume doGet(Path path) {
        return null;
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    protected List<Resume> getAllElements() {
        return getFilesList()
                .map(this::doGet)
                .collect(Collectors.toList());
    }

    protected Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
    }
}
