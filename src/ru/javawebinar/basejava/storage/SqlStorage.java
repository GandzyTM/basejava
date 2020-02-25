package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void save(Resume resume) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into resumedb.resume.resume (uuid, full_name) VALUES (?, ?)");
             PreparedStatement checkPreparedStatement = connection.prepareStatement("select r.uuid from resume.resume r where r.uuid = ?")) {
            checkPreparedStatement.setString(1, resume.getUuid());
            ResultSet resultSet = checkPreparedStatement.executeQuery();
            if (resultSet.next()) {
                throw new ExistStorageException(resume.getUuid());
            }
            preparedStatement.setString(1, resume.getUuid());
            preparedStatement.setString(2, resume.getFullName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public Resume get(String uuid) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from resumedb.resume.resume where uuid = ?")) {
            preparedStatement.setString(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, resultSet.getString("full_name"));
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from resumedb.resume.resume order by full_name, uuid")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Resume> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Resume(resultSet.getString("uuid"), resultSet.getString("full_name")));
            }
            return list;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(String uuid) {
        // checking for Not Exist
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from resumedb.resume.resume where uuid = ?");
             PreparedStatement checkPreparedStatement = connection.prepareStatement("select r.uuid from resume.resume r where r.uuid = ?")) {
            checkPreparedStatement.setString(1, uuid);
            ResultSet resultSet = checkPreparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new NotExistStorageException(uuid);
            }
            preparedStatement.setString(1, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void update(Resume resume) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update resumedb.resume.resume set full_name = ? where uuid = ?")) {
            preparedStatement.setString(1, resume.getFullName());
            preparedStatement.setString(2, resume.getUuid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void clear() {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from resumedb.resume.resume")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public int size() {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from resumedb.resume.resume")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Integer.parseInt(resultSet.getString("count"));
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
        return 0;
    }
}
