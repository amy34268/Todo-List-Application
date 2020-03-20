package ui;


import model.Task;
import model.ToDoList;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.JOptionPane;


public class ListExample extends JPanel implements ActionListener {

    private static final String TODOLISTS_FILE = "./data/todolists.txt";
    JList list;
    DefaultListModel listModel;

    //ADD and DELETE buttons
    private JButton addTask;
    private JButton deleteTask;
    private JButton labelTask;
    private JButton save;
    private JButton load;


    //User input for new Task's name
    private JTextField taskInput;
    private JTextField labelInput;

    //!!! For now, it will reflect the input change
    private JLabel changed;

    private static ToDoList toDoList;

    public ListExample() {
        super(new BorderLayout());

        listModel = new DefaultListModel();
       // toDoList = new ToDoList();

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(10);
        JScrollPane sp = new JScrollPane(list);

        list.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    String name = toDoList.getTaskPos(list.getSelectedIndex()).getName();
                    String message = "Deadline: \n" + "Label:    "
                            + toDoList.getTaskPos(list.getSelectedIndex()).getLabel();

                    JOptionPane.showMessageDialog(sp, message,
                            name, JOptionPane.PLAIN_MESSAGE); //!!!Add Image?

                }
            }
        });

        addTask = new JButton("ADD");
        addTask.setActionCommand("ADD");
        addTask.addActionListener(this);

        deleteTask = new JButton("DELETE");
        deleteTask.setActionCommand("DELETE");
        deleteTask.addActionListener(this);

        labelTask = new JButton("LABEL");
        labelTask.setActionCommand("LABEL");
        labelTask.addActionListener(this);

        save = new JButton(("SAVE"));
        save.setActionCommand(("SAVE"));
        save.addActionListener(this);

        load = new JButton(("LOAD"));
        load.setActionCommand(("LOAD"));
        load.addActionListener(this);


        changed = new JLabel("change");


        taskInput = new JTextField(5);

        labelInput = new JTextField(5);


        //create a panel that uses BoxLayout;

        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp,
                BoxLayout.LINE_AXIS));
        bp.add(addTask);
        bp.add(taskInput);
        bp.add(deleteTask);
        bp.add(labelTask);
        bp.add(labelInput);
        bp.add(save);
        bp.add(load);
        bp.add(changed);

        add(sp, BorderLayout.CENTER);
        add(bp, BorderLayout.PAGE_END);


    }

    //The method that's called when ADD or DELETE button is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADD")) {
            listModel.addElement(taskInput.getText());

            toDoList.addTask(new Task(taskInput.getText()));

            taskInput.requestFocusInWindow();
            taskInput.setText("");


            list.setSelectedIndex(listModel.size() - 1);
            list.ensureIndexIsVisible(listModel.size() - 1);


        }
        if (e.getActionCommand().equals("DELETE")) {
            int index = list.getSelectedIndex();
            listModel.remove(list.getSelectedIndex());

            for (Task task : toDoList.getToDoList()) {
                if (task.getName().equals(listModel.get(index))) {
                    toDoList.deleteTask(task);
                }
            }

            taskInput.requestFocusInWindow();
            taskInput.setText("");

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
        if (e.getActionCommand().equals("LABEL")) {
            int index = list.getSelectedIndex();

            changed.setText(toDoList.getTaskPos(index).addTaskLabel(labelInput.getText()));

            labelInput.requestFocusInWindow();
            labelInput.setText("");
        }
        if (e.getActionCommand().equals("SAVE")) {
            saveToDoLists();
        }
        if (e.getActionCommand().equals("LOAD")) {
            loadToDoLIstToDisplayList();
        }


    }

    public void loadToDoLIstToDisplayList() {
        for (Task task : toDoList.getToDoList()) {
            listModel.addElement(task.getName());
        }
    }


    // MODIFIES: this
    // EFFECTS: loads toDoList from TODOLISTS_FILE, if that file exists;
    // otherwise initializes toDoList with default values
    private static void loadToDoLists() {
        try {
            toDoList = new ToDoList();
            ToDoList oldL = Reader.readTask(new File(TODOLISTS_FILE));
            toDoList.addToDoList(oldL);


        } catch (IOException e) {
            init();
        }
    }
    // MODIFIES: this
    // EFFECTS: initializes to-do list
    private static void init() {
        toDoList = new ToDoList();
    }


    private void saveToDoLists() {
        try {
            Writer writer = new Writer(new File(TODOLISTS_FILE));
            writer.write(toDoList);
            writer.close();
            System.out.println("Accounts saved to file " + TODOLISTS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + TODOLISTS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
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
                loadToDoLists();
            }
        });
    }

}