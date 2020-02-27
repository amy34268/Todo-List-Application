package model;

import persistence.Saveable;

import java.io.Reader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// Represents a to do list with a list of tasks

public class ToDoList implements Saveable {
    public static final String DELIMITER = ",";

    private List<Task> toDoList;
    //private String name; // the to-do list name

    //REQUIRES: todoName has a non-zero length
    // EFFECTS: constructs an empty to do list
    public ToDoList() {
        toDoList = new ArrayList<>();
        //  name = todoName;
    }

    public void addToDoList(Task task) {
        toDoList.add(task);
    }

    public void addToDoList(ToDoList oldList) {
        for (int y = 0; y < oldList.getNumToDoList(); y++) {
            addToDoList(oldList.getTaskPos(y));
        }
    }

    // MODIFIES: this
    // EFFECTS: add a task to the list of to-dos
    public void addTask(Task task) {
        toDoList.add(task);
    }

    // MODIFIES: this
    // EFFECTS: delete a task from the list of to-dos
    public void deleteTask(Task task) {
        toDoList.remove(task);
    }


    public List<Task> getToDoList() {
        return toDoList;
    }

    public Task getTaskPos(int index) {
        return toDoList.get(index);
    }


    public int getNumToDoList() {
        return toDoList.size();
    }

    @Override
    public void save(PrintWriter printWriter) {
        for (int i = 0; i < getToDoList().size(); i++) {

            printWriter.write(toDoList.get(i).getName());
            printWriter.write(DELIMITER);
            printWriter.write(toDoList.get(i).getLabel());
            printWriter.write(DELIMITER);
            printWriter.print(toDoList.get(i).getStatus());
            printWriter.write(DELIMITER);
            printWriter.print(toDoList.get(i).getDeadline());
            printWriter.write(DELIMITER);
            printWriter.write("\n");
        }


    }
}
