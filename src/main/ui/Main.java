package ui;

import model.exceptions.InputInvalidException;

import javax.swing.*;

import static ui.ToDoListGUI.loadToDoLists;

public class Main {

    //EFFECTS: run a to-do list gui to be able to track tasks
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                runForMain();
                loadToDoLists();
            }
        });
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


}
