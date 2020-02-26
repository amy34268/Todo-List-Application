package model;

import java.util.ArrayList;
import java.util.List;

// Represents a to do list with a list of tasks

public class ToDoList {

    private List<Task> toDoList;
    private String name; // the to-do list name

    //REQUIRES: todoName has a non-zero length
    // EFFECTS: constructs an empty to do list
    public ToDoList(String todoName) {
        toDoList = new ArrayList<>();
        name = todoName;
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
}
