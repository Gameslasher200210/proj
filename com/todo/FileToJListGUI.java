package com.todo;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileToJListGUI {
    private JFrame frame;
    private JPanel panel;
    private JList<String> uncompletedList;
    private JList<String> completedList;
    public DefaultListModel<String> uncompletedListModel;
    public DefaultListModel<String> completedListModel;

    public FileToJListGUI() {
        frame = new JFrame("File To JList GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        panel = new JPanel(new GridLayout(1, 2));

        uncompletedListModel = new DefaultListModel<>();
        uncompletedList = new JList<>(uncompletedListModel);
        JScrollPane uncompletedScrollPane = new JScrollPane(uncompletedList);

        completedListModel = new DefaultListModel<>();
        completedList = new JList<>(completedListModel);
        JScrollPane completedScrollPane = new JScrollPane(completedList);

        panel.add(uncompletedScrollPane);
        panel.add(completedScrollPane);

        frame.add(panel);
        frame.setVisible(true);

        displayFileContent(uncompletedListModel, "uncompleted.txt");
        displayFileContent(completedListModel, "completed.txt");
    }

    public static void displayFileContent(DefaultListModel<String> listModel, String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                listModel.addElement(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            listModel.addElement("Error reading file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FileToJListGUI());
    }
}
