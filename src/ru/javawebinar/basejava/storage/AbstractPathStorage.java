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
import java.util.function.Consumer;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory can not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or not writable");
        }
        this.directory = directory;
    }

    protected abstract void doWrite(Resume resume, OutputStream outputStream) throws IOException;

    protected abstract Resume doRead(InputStream inputStream) throws IOException;

    @Override
    protected Path getSearchKey(String uuid) {
        return new Path(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume resume, Path Path) {
        try {
            doWrite(resume, new BufferedOutputStream(new PathOutputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("IO error", Path.getName(), e);
        }
    }

    @Override
    protected boolean isExist(Path Path) {
        return Path.exists();
    }

    @Override
    protected void doSave(Resume resume, Path Path) {
        try {
            Path.createNewPath();
        } catch (IOException e) {
            throw new StorageException("IO error", resume.getUuid(), e);
        }
        doUpdate(resume, Path);
    }

    @Override
    protected Resume doGet(Path Path) {
        try {
            return doRead(new BufferedInputStream(new PathInputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("IO error", Path.getName(), e);
        }
    }

    @Override
    protected void doDelete(Path Path) {
        if (!Path.delete()) {
            throw new StorageException("Can not delete Path", Path.getName());
        }
    }

    @Override
    protected List<Resume> getAllElements() {
        Path[] Paths = getPathsList();
        List<Resume> list = new ArrayList<>(Paths.length);
        for (Path f : Paths) {
            list.add(doGet(f));
        }
        return list;
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
        return getPathsList().length;
    }

    protected Path[] getPathsList() {
        Path[] Paths = directory.listPaths();
        if (Paths != null) {
            return Paths;
        } else {
            throw new StorageException("No Paths in directory", null);
        }
    }
}
