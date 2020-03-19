package ui;


import model.Task;
import model.ToDoList;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.JOptionPane;


public class ListExample extends JPanel implements ActionListener {

    private JList list;
    private DefaultListModel listModel;

    //ADD and DELETE buttons
    private JButton addTask;
    private JButton deleteTask;
    private JButton labelTask;


    //User input for new Task's name
    private JTextField taskInput;
    private JTextField labelInput;

    //!!! For now, it will reflect the input change
    private JLabel changed;


    private ToDoList toDoList;

    public ListExample() {
        super(new BorderLayout());

        listModel = new DefaultListModel();
        toDoList = new ToDoList();

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(10);
        JScrollPane sp = new JScrollPane(list);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // list.getSelectedIndex();

                list.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            JOptionPane.showMessageDialog(sp,
                                    "Eggs are not supposed to be green.",
                                    "A plain message",
                                    JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                });
            }
        });

        addTask = new JButton("ADD");
        addTask.setActionCommand("ADD");
        addTask.addActionListener(this);

        deleteTask = new

                JButton("DELETE");
        deleteTask.setActionCommand("DELETE");
        deleteTask.addActionListener(this);

        labelTask = new

                JButton("LABEL");
        labelTask.setActionCommand("LABEL");
        labelTask.addActionListener(this);

        changed = new

                JLabel("change");


        taskInput = new

                JTextField(5);

        labelInput = new

                JTextField(5);


        //create a panel that uses BoxLayout;

        JPanel bp = new JPanel();
        bp.setLayout(new

                BoxLayout(bp,
                BoxLayout.LINE_AXIS));
        bp.add(addTask);
        bp.add(taskInput);
        bp.add(deleteTask);
        bp.add(labelTask);
        bp.add(labelInput);
        bp.add(changed);

        add(sp, BorderLayout.CENTER);

        add(bp, BorderLayout.PAGE_END);


    }

    //The method that's called when ADD or DELETE button is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADD")) {
            listModel.addElement(taskInput.getText());
            taskInput.requestFocusInWindow();
            taskInput.setText("");

            toDoList.getToDoList().add(new Task(taskInput.getText()));

            list.setSelectedIndex(listModel.size() - 1);
            list.ensureIndexIsVisible(listModel.size() - 1);


        }
        if (e.getActionCommand().equals("DELETE")) {
            int index = list.getSelectedIndex();
            listModel.remove(list.getSelectedIndex());

            for (Task task : toDoList.getToDoList()) {
                if (task.getName().equals(listModel.get(index))) {
                    toDoList.getToDoList().remove(task);
                }
            }

            taskInput.requestFocusInWindow();
            taskInput.setText("");

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
        if (e.getActionCommand().equals("LABEL")) {
            int index = list.getSelectedIndex();

            changed.setText(toDoList.getToDoList().get(index).addTaskLabel(labelInput.getText()));

            labelInput.requestFocusInWindow();
            labelInput.setText("");
        }

    }


    private static void runForMain() {
        JFrame frame = new JFrame("Project Name");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ListExample listExample = new ListExample();
        listExample.setOpaque(true);
        frame.setContentPane(listExample);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                runForMain();
            }
        });
    }

}