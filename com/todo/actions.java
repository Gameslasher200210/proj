package com.todo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class actions extends JFrame {
    private JList<String> dataList;

    public actions() {
        setTitle("File to JList GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        dataList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(dataList);
        add(scrollPane, BorderLayout.CENTER);

        loadFileData("example.txt"); // Change to your file path

        setLocationRelativeTo(null);
    }
     public void removeAllItemsFromJList() {
        DefaultListModel<String> model = new DefaultListModel<>();
        dataList.setModel(model);
    }
    private void loadFileData(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] dataArray = lines.toArray(new String[0]);
        dataList.setListData(dataArray);
    }
    
    public void add(String fileName, String contentToAdd) {
        try {
            // Step 1: Open the file in append mode using FileWriter and BufferedWriter
            FileWriter fileWriter = new FileWriter(fileName, true); // true indicates append mode
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Step 2: Write the content to the file
            bufferedWriter.write(contentToAdd);
            bufferedWriter.newLine(); // Optional: Add a new line after the content

            // Step 3: Close the file to save changes
            bufferedWriter.close();

            System.out.println("Content has been added to the file.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public void remove(String fileName, String contentToRemove) {
        try {
            File inputFile = new File(fileName);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // Check if the line contains the text to remove, if not, write it to the temporary file
                if (!currentLine.contains(contentToRemove)) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }

            writer.close();
            reader.close();

            // Delete the original file
            if (!inputFile.delete()) {
                System.err.println("Error deleting the original file.");
                return;
            }

            // Rename the temporary file to the original file name
            if (!tempFile.renameTo(inputFile)) {
                System.err.println("Error renaming the temporary file.");
            }

            System.out.println("Text removed successfully.");
        } catch (IOException e) {
            System.err.println("Error removing text from the file: " + e.getMessage());
        }
    }

    public void update(String fileName, String newContent, int lineToUpdate) {
        // Read the file, update the specified line, and write the updated content back to the file
        try {
            File inputFile = new File(fileName);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            int currentLineNum = 1;

            while ((currentLine = reader.readLine()) != null) {
                // Check if the current line is the one to update
                if (currentLineNum == lineToUpdate) {
                    writer.write(newContent);
                } else {
                    writer.write(currentLine);
                }
                writer.newLine();
                currentLineNum++;
            }

            writer.close();
            reader.close();

            // Delete the original file
            if (!inputFile.delete()) {
                System.err.println("Error deleting the original file.");
                return;
            }

            // Rename the temporary file to the original file name
            if (!tempFile.renameTo(inputFile)) {
                System.err.println("Error renaming the temporary file.");
            }

            System.out.println("Line updated successfully.");
        } catch (IOException e) {
            System.err.println("Error updating line in the file: " + e.getMessage());
        }
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
        
        });
    }
    }