package ui;


import com.sun.org.apache.bcel.internal.util.ClassVector;
import javafx.scene.control.Labeled;
import model.Task;
import model.ToDoList;

import model.exceptions.InputInvalidException;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JOptionPane;


import static java.lang.Double.parseDouble;


public class ToDoListGUI extends JPanel implements ActionListener {

    private static final String TODOLISTS_FILE = "./data/todolists.txt";
    private static final String ADD = "ADD";
    private static final String DELETE = "DELETE";
    private static final String LABEL = "LABEL";
    private static final String DATE = "DATE";
    private static final String SAVE = "SAVE";
    private static final String LOAD = "LOAD";
    private static final String COMPLETE = "COMPLETE";



    private JList list;
    private DefaultListModel listModel;
    private JScrollPane sp;
    private static ToDoList toDoList;

    //All buttons layout on the GUI
    private JButton addTask;
    private JButton deleteTask;
    private JButton labelTask;
    private JButton dateTask;
    private JButton save;
    private JButton load;
    private JButton completed;


    //User input for Task's information
    private JTextField taskInput;
    private JTextField labelInput;
    private JTextField dateInput;

    private ImageIcon icon = new ImageIcon("to-do.png");

    //EFFECTS: Construct a to-do list graphical user interface
    public ToDoListGUI() {
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

        panelSetUp(bp);

        add(sp, BorderLayout.CENTER);
        add(bp, BorderLayout.PAGE_END);

    }

    //EFFECTS: a window that pops up responding to a pressed key
    private void keyPressedHelper(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String name = toDoList.getTaskPos(list.getSelectedIndex()).getName();
            String message = "Deadline:   " + toDoList.getTaskPos(list.getSelectedIndex()).getDeadline()
                    + "\nLabel:    " + toDoList.getTaskPos(list.getSelectedIndex()).getLabel()
                    + "\nStatus:    " + toDoList.getTaskPos(list.getSelectedIndex()).getStringStatus();
            icon = new ImageIcon("./data/todo.png");
            Image image = icon.getImage();
            Image icon2 = image.getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(icon2);
            JOptionPane.showMessageDialog(sp, message, name, JOptionPane.PLAIN_MESSAGE, icon);
        }
    }

    //EFFECTS: initialize all buttons
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

    //EFFECTS: initialize a displayable panel
    private void panelSetUp(JPanel bp) {

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

    //EFFECTS: initialize add button
    private void addButton() {

        addTask = new JButton(ADD);
        addTask.setActionCommand(ADD);
        addTask.addActionListener(this);
    }

    //EFFECTS: initialize delete button
    private void deleteButton() {
        deleteTask = new JButton(DELETE);
        deleteTask.setActionCommand(DELETE);
        deleteTask.addActionListener(this);
    }

    //EFFECTS: initialize label button
    private void labelButton() {
        labelTask = new JButton(LABEL);
        labelTask.setActionCommand(LABEL);
        labelTask.addActionListener(this);
    }

    //EFFECTS: initialize date button
    private void dateButton() {
        dateTask = new JButton(DATE);
        dateTask.setActionCommand(DATE);
        dateTask.addActionListener(this);
    }

    //EFFECTS: initialize save button
    private void saveButton() {
        save = new JButton((SAVE));
        save.setActionCommand((SAVE));
        save.addActionListener(this);
    }

    //EFFECTS: initialize load button
    private void loadButton() {
        load = new JButton((LOAD));
        load.setActionCommand((LOAD));
        load.addActionListener(this);
    }

    //EFFECTS: initialize complete button
    private void completedButton() {
        completed = new JButton((COMPLETE));
        completed.setActionCommand((COMPLETE));
        completed.addActionListener(this);
    }


    // MODIFIES: listmodel, todolist
    // EFFECTS:  add task to according to  index
    private void addCommand() {
        listModel.addElement(taskInput.getText());

        toDoList.addTask(new Task(taskInput.getText()));

        taskInput.requestFocusInWindow();
        taskInput.setText("");

        list.setSelectedIndex(listModel.size() - 1);
        list.ensureIndexIsVisible(listModel.size() - 1);

    }

    // MODIFIES: listmodel, todolist
    // EFFECTS: remove task in lists  according to index
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

    // MODIFIES: todolist
    // EFFECTS: set the label of the tas  in the todolist to the according index
    private void labelCommand() {
        int index = list.getSelectedIndex();

        toDoList.getTaskPos(index).addTaskLabel(labelInput.getText());

        labelInput.requestFocusInWindow();
        labelInput.setText("");
    }


    // MODIFIES: todolist
    // EFFECTS: set the date of the tas  in the todolist to the according index
    // throws InputInvalidException if input is invalid
    private void dateCommand() throws InputInvalidException {
        int index = list.getSelectedIndex();
        double dateNum = parseDouble(dateInput.getText());
        toDoList.getTaskPos(index).addTaskDate(parseDouble(dateInput.getText()));
        dateInput.requestFocusInWindow();
        dateInput.setText("");

    }

    // MODIFIES: todolist
    // EFFECTS: set the status of the task to true  in the todolist to the according index
    private void completeCommand() {
        int index = list.getSelectedIndex();

        toDoList.getTaskPos(index).setStatus(true);
    }


    //EFFECT: determine which method that's called when the according  button is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ADD)) {
            addCommand();
        }
        if (e.getActionCommand().equals(DELETE)) {
            deleteCommand();
        }
        if (e.getActionCommand().equals(LABEL)) {
            labelCommand();
        }
        if (e.getActionCommand().equals(DATE)) {
            dateCommandWithException();
        }
        if (e.getActionCommand().equals(COMPLETE)) {
            completeCommand();
        }
        if (e.getActionCommand().equals(SAVE)) {
            saveToDoLists();
        }
        if (e.getActionCommand().equals(LOAD)) {
            loadToDoLIstToDisplayList();
        }

    }

    //EFFECTS: helper function when date button is clicked, if input is invalid, display a warning window;
    //          otherwise do dateCommand()
    public void dateCommandWithException() {
        try {
            dateCommand();

        } catch (InputInvalidException e) {
            JOptionPane.showMessageDialog(sp, "The date you entered is incorrect", "InvalidInput",
                    JOptionPane.WARNING_MESSAGE);
        }
    }


    // MODIFIES: listModel
    // EFFECTS: loads to-doList tasks' names to the listModel so it is displayable

    public void loadToDoLIstToDisplayList() {
        for (Task task : toDoList.getToDoList()) {
            listModel.addElement(task.getName());
        }
        load.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: loads toDoList from TODOLISTS_FILE, if that file exists;
    // otherwise initializes toDoList with default values

    private static void loadToDoLists() {
        try {
            toDoList = new ToDoList();
            ToDoList oldL = Reader.readTask(new File(TODOLISTS_FILE));
            toDoList.addToDoList(oldL);

        } catch (IOException | InputInvalidException e) {
            toDoList = new ToDoList();
        }
    }


    // EFFECTS: write to do-do list information to file
    private void saveToDoLists() {
        try {
            Writer writer = new Writer(new File(TODOLISTS_FILE));
            writer.write(toDoList);
            writer.close();
            System.out.println("To-do list information is  saved to file " + TODOLISTS_FILE);
            save.setEnabled(false);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to-do list information to " + TODOLISTS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: initial a frame for to-do list GUI
    private static void runForMain() {
        JFrame frame = new JFrame("My ToDo List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ToDoListGUI toDoListGUI = new ToDoListGUI();
        toDoListGUI.setOpaque(true);
        frame.setContentPane(toDoListGUI);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    //EFFECTS: run a to-do list gui to be able to track tasks
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                runForMain();
                loadToDoLists();
            }
        });
    }


}