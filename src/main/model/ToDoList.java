package model;

import persistence.Saveable;

import java.io.Reader;
import java.io.PrintWriter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static persistence.Reader.DELIMITER;

// Represents a to do list with a list of tasks

public class ToDoList implements Saveable  {
    private ArrayList<Task> toDoList;


    // EFFECTS: constructs an empty to do list
    public ToDoList() {
        toDoList = new ArrayList<>();
    }

    //EFFECTS:  add all the old to-do list tasks to the new to-do list
    public void addToDoList(ToDoList oldToDoList) {
        for (int x = 0; x < oldToDoList.getNumToDoList(); x++) {
            addTask(oldToDoList.getTaskPos(x));
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

    // EFFECTS: returns the to-do list with tasks
    public List<Task> getToDoList() {
        return toDoList;
    }

    // EFFECTS: returns the task in the to-do list at the provided index
    public Task getTaskPos(int index) {
        return toDoList.get(index);
    }

    // EFFECTS: returns the size of the to-do list
    public int getNumToDoList() {
        return toDoList.size();
    }



    // EFFECTS: write out the info of the to-do list as strings
    @Override
    public void save(PrintWriter printWriter) {
        for (int i = 0; i < getNumToDoList(); i++) {

            printWriter.write(toDoList.get(i).getName());
            printWriter.write(DELIMITER);
            printWriter.print(toDoList.get(i).getDeadline());
            printWriter.write(DELIMITER);
            printWriter.write(toDoList.get(i).getLabel());
            printWriter.write(DELIMITER);
            printWriter.print(toDoList.get(i).getStatus());
            printWriter.write(DELIMITER);
            printWriter.write("\n");
        }


    }
}
