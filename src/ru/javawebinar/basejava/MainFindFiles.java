package ru.javawebinar.basejava;

import java.io.File;

public class MainFindFiles {
    public static void main(String[] args) {
        File file = new File("./src");
        showFilesDirectories(file);
    }

    public static void showFilesDirectories(File file) {
        File[] files = file.listFiles();
        for (File p : files) {
            if (!p.isDirectory()) {
                System.out.println(p.getName());
            }
            if (p.isDirectory()) {
                try {
                    showFilesDirectories(p);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
