package com.commonUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;

public class FolderReplacement {

    public static void folderReplace(String folderPath) {
        // Define the folder path
        
        // Delete the existing folder and its contents
        deleteFolder(new File(folderPath));

        // Create a new folder
        File newFolder = new File(folderPath);
        if (newFolder.mkdirs()) {
            System.out.println("Folder created successfully!");
        } else {
            System.out.println("Failed to create the folder.");
        }
    }

    // Method to delete a folder and its contents
    public static void deleteFolder(File folder) {
        if (folder.isDirectory()) {
            // Recursively delete subfolders and files
            File[] files = folder.listFiles();
            if (files != null) { // Check for null to avoid NullPointerException
                for (File file : files) {
                    deleteFolder(file);
                }
            }
        }
        if (folder.delete()) {
            System.out.println("Deleted: " + folder.getAbsolutePath());
        } else {
            System.out.println("Failed to delete: " + folder.getAbsolutePath());
        }
    }
}
