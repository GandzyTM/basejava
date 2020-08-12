package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
//    protected static final File PROPS = new File("C:\\Users\\pashkov-kv\\Documents\\GitHub\\basejava\\config\\resumes.properties");
    protected static final File PROPS = new File("C:\\Users\\GANDZY\\Documents\\GitHub\\basejava\\config\\resumes.properties");
    private static final Config INSTANCE = new Config();
    private File storageDir;
    private String url;
    private String user;
    private String password;

    public static Config getInstance() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream inputStream = new FileInputStream(PROPS)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            storageDir = new File(properties.getProperty("storage.dir"));
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
