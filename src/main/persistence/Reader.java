package persistence;

import model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read to-do list data from a file
public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of task parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static ToDoList readTask(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of to-do list parsed from list of strings
    // where each string contains data for one to-do list
    private static ToDoList parseContent(List<String> fileContent) {
        ToDoList toDoLists = new ToDoList();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            toDoLists.addTask(parseTask(lineComponents));
        }

        return toDoLists;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 4 where element 0 represents the
    // id of the next account to be constructed, element 1 represents
    // the id, elements 2 represents the name and element 3 represents
    // the balance of the account to be constructed
    // EFFECTS: returns an to-do list constructed from components
    private static Task parseTask(List<String> components) {
        String name = components.get(0);
        double deadline = Double.parseDouble(components.get(1));
        String label = components.get(2);
        Boolean status = Boolean.parseBoolean(components.get(3));;

        Task newTask = new Task(name);
        newTask.addTaskDate(deadline);
        newTask.addTaskLabel(label);
        //!!!
        return newTask;
    }
}
