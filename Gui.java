import com.todo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Gui extends JFrame {
    // Initializing swing components
    public JFrame frame;
    private JButton add;
    private JTextField task;
    private JPanel panelTop;
    private JPanel uncompleted; 
    private List<String> dataList = new ArrayList<>();
        public static DefaultListModel<String> uncompletedListModel;
    public static DefaultListModel<String> completedListModel;

    private void removeAllItemsFromList() {
        dataList.clear();
    }


    public Gui() {
        setTitle("File to JList GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        panelTop = new JPanel();

        // Create a button
        add = new JButton("+");
        task = new JTextField(30);
        panelTop.add(add);
        panelTop.add(task);
        task.setText("ADD A TASK");
        add(panelTop);
        
        /*dataList = new JList<>();
        JPane scrollPane = new JPane(dataList);
        add(scrollPane)

        loadFileData("completed.txt"); // Change to your file path

        setLocationRelativeTo(null);*/

        
        // Listener for "add" button
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = task.getText();
                actions act = new actions();
                act.add("=uncompleted.txt", inputText);
                   // Clear the text field after adding the task
                task.setText("ADD A TASK");
            }
        });

        // Register an event handler for mouse click on the "task" text field
        task.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getSource() == task) {
                    task.setText("");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Gui().setVisible(true);

        });
    }
}