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

import static java.lang.Double.parseDouble;


public class ListExample extends JPanel implements ActionListener {

    private static final String TODOLISTS_FILE = "./data/todolists.txt";
    private JList list;
    private DefaultListModel listModel;
    private JScrollPane sp;

    //ADD and DELETE buttons
    private JButton addTask;
    private JButton deleteTask;
    private JButton labelTask;
    private JButton dateTask;
    private JButton save;
    private JButton load;
    private JButton completed;


    //User input for new Task's name
    private JTextField taskInput;
    private JTextField labelInput;
    private JTextField dateInput;


    //!!! For now, it will reflect the input change
    // private JLabel changed;

    private static ToDoList toDoList;

    public ListExample() {
        super(new BorderLayout());
        listModel = new DefaultListModel();
        // toDoList = new ToDoList();

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(10);
        sp = new JScrollPane(list);

        list.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                keyPressedHelper(e);
            }
        });

        allButtonsAndInPuts();

        JPanel bp = new JPanel();
        bp.setLayout(new

                BoxLayout(bp,
                BoxLayout.LINE_AXIS));

        panelSetUP(bp);

        add(sp, BorderLayout.CENTER);

        add(bp, BorderLayout.PAGE_END);

    }

    private void keyPressedHelper(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String name = toDoList.getTaskPos(list.getSelectedIndex()).getName();
            String message = "Deadline:   " + toDoList.getTaskPos(list.getSelectedIndex()).getDeadline()
                    + "\nLabel:    " + toDoList.getTaskPos(list.getSelectedIndex()).getLabel()
                    + "\nStatus:    " + toDoList.getTaskPos(list.getSelectedIndex()).getStringStatus();
            JOptionPane.showMessageDialog(sp, message, name, JOptionPane.PLAIN_MESSAGE);
        }
    }

  


    private void allButtonsAndInPuts() {
        addButton();
        deleteButton();
        labelButton();
        dateButton();
        completedButton();
        saveButton();
        loadButton();

        taskInput = new JTextField(5);
        labelInput = new JTextField(5);
        dateInput = new JTextField(5);
    }

    private void paneSetUp(JPanel bp) {

        bp.setLayout(new BoxLayout(bp,
                BoxLayout.LINE_AXIS));
        bp.add(addTask);
        bp.add(taskInput);
        bp.add(deleteTask);
        bp.add(labelTask);
        bp.add(labelInput);
        bp.add(dateTask);
        bp.add(dateInput);
        bp.add(completed);
        bp.add(save);
        bp.add(load);

    }

    private void addButton() {

        addTask = new JButton("ADD");
        addTask.setActionCommand("ADD");
        addTask.addActionListener(this);
    }

    private void deleteButton() {
        deleteTask = new JButton("DELETE");
        deleteTask.setActionCommand("DELETE");
        deleteTask.addActionListener(this);
    }

    private void labelButton() {
        labelTask = new JButton("LABEL");
        labelTask.setActionCommand("LABEL");
        labelTask.addActionListener(this);
    }

    private void dateButton() {
        dateTask = new JButton("DATE");
        dateTask.setActionCommand("DATE");
        dateTask.addActionListener(this);
    }


    private void saveButton() {
        save = new JButton(("SAVE"));
        save.setActionCommand(("SAVE"));
        save.addActionListener(this);
    }

    private void loadButton() {
        load = new JButton(("LOAD"));
        load.setActionCommand(("LOAD"));
        load.addActionListener(this);
    }


    private void completedButton() {
        completed = new JButton(("COMPLETE"));
        completed.setActionCommand(("COMPLETE"));
        completed.addActionListener(this);
    }

  

    private void addCommand() {
        listModel.addElement(taskInput.getText());

        toDoList.addTask(new Task(taskInput.getText()));

        taskInput.requestFocusInWindow();
        taskInput.setText("");

        list.setSelectedIndex(listModel.size() - 1);
        list.ensureIndexIsVisible(listModel.size() - 1);

    }


    private void deleteCommand() {
        int index = list.getSelectedIndex();
        listModel.remove(list.getSelectedIndex());

        for (Task task : toDoList.getToDoList()) {
            if (task.getName().equals(listModel.get(index))) {
                toDoList.deleteTask(task);
            }
        }

        list.setSelectedIndex(index);
        list.ensureIndexIsVisible(index);
    }

    private void labelCommand() {
        int index = list.getSelectedIndex();

        toDoList.getTaskPos(index).addTaskLabel(labelInput.getText());

        labelInput.requestFocusInWindow();
        labelInput.setText("");
    }


    private void dateCommand() {
        int index = list.getSelectedIndex();

        toDoList.getTaskPos(index).addTaskDate(parseDouble(dateInput.getText()));
        dateInput.requestFocusInWindow();
        dateInput.setText("");
    }

    private void completeCommand() {
        int index = list.getSelectedIndex();

        toDoList.getTaskPos(index).setStatus(true);
    private void labelCommand() {
        int index = list.getSelectedIndex();

        toDoList.getTaskPos(index).addTaskLabel(labelInput.getText());

        labelInput.requestFocusInWindow();
        labelInput.setText("");
    }

    }

    //The method that's called when ADD or DELETE button is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADD")) {
            addCommand();
        }
        if (e.getActionCommand().equals("DELETE")) {
            deleteCommand();
        }
        if (e.getActionCommand().equals("LABEL")) {
            labelCommand();
        }
        if (e.getActionCommand().equals("DATE")) {
            dateCommand();
        }
        if (e.getActionCommand().equals("COMPLETE")) {
            completeCommand();
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