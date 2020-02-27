package ui;

import model.Task;
import model.ToDoList;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

//To-Do List application
public class ToDoListApp {
    private static final String TODOLISTS_FILE = "./data/todolists.txt";
    private ToDoList toDoList;
    private Scanner input;

    //EFFECTS: Run the to-do list application
    public ToDoListApp() {
        runToDoList();
        toDoList = new ToDoList();


    }

    //MODIFIES: this
    //EFFECTS: processes user input and initialize a to-do list
    private void runToDoList() {
        boolean keepGoing = true;
        String command = "";

        input = new Scanner(System.in);

         loadToDoLists();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    public void processCommand(String command) {
        switch (command) {
            case "a":

                doAddTask();
                break;
            case "d":

                doDeleteTask();
                break;
            case "date":

                doAddTaskDate();
                break;
            case "l":

                doAddTaskLabel();
                break;
            case "v":

                displayTasks();
                break;
            case "s":

                saveToDoLists();
                break;
        }

    }


    //EFFECTS: display menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta-> add task");
        System.out.println("\td-> delete task");
        System.out.println("\tdate-> add date to a task");
        System.out.println("\tl-> add label to a task");
        System.out.println("\tv-> view current tasks");
        System.out.println("\ts-> save todo list to file");
        System.out.println("\tq-> quit");

    }

    // MODIFIES: this
    // EFFECTS: initializes to-do list
    private void init() {
        toDoList = new ToDoList();
    }


    // MODIFIES: this
    // EFFECTS: loads toDoList from TODOLISTS_FILE, if that file exists;
    // otherwise initializes toDoList with default values
    private void loadToDoLists() {
        try {
            ToDoList oldL = Reader.readTask(new File(TODOLISTS_FILE));
            this.toDoList.addToDoList(oldL);

        } catch (IOException e) {
            init();
        }
    }

    // EFFECTS: saves state of toDoLists to TODOLISTS_FILE
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


    //MODIFIES: this
    //EFFECTS: add user entry as a task to the to do list
    private void doAddTask() {
        System.out.println("Enter your task name:");

        String name = input.next();

        Task x = new Task(name,0.0,"",false);
        toDoList.addTask(x);
    }

    //MODIFIES: this
    //EFFECTS: remove user entry of a task from the to do list
    private void doDeleteTask() {
        System.out.println("choose your task:");
        displayTasks();
        String enter = input.next();
        List<Task> tasks = toDoList.getToDoList();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getName().equals(enter)) {
                toDoList.deleteTask(task);
                System.out.println("your task is now deleted");
                return;
            }
        }
        System.out.println("Task is not found");

    }

    
    //MODIFIES: this
    //EFFECTS: add a date the user entry's task in the to do list
    private void doAddTaskDate() {
        System.out.println("choose your task:");
        displayTasks();
        String enter = input.next();
        System.out.println("Enter your deadline date in the format month.day: ");
        double date = input.nextDouble();

        for (Task task : toDoList.getToDoList()) {
            if (task.getName().equals(enter)) {
                System.out.println("Your task now has the deadline:" + task.addTaskDate(date));
                return;
            }
        }
        System.out.println("Task is not found");

    }

    //MODIFIES: this
    //EFFECTS: add a label to the user entry's task in the to do list
    private void doAddTaskLabel() {
        System.out.println("choose your task:");
        displayTasks();
        String enter = input.next();

        System.out.println("choose your colour:1 is black, 2 is white");
        int colour = input.nextInt();

        for (Task task : toDoList.getToDoList()) {

            if (task.getName().equals(enter)) {
                System.out.println("Your task now has the label:" + task.addTaskLabel(colour));
                return;
            }

        }
        System.out.println("Task is not found");
    }

    //EFFECTS: display current available tasks
    private void displayTasks() {

        for (Task task : toDoList.getToDoList()) {
            System.out.println("Task Name:" + task.getName() + "\t,Deadline: " + task.getDeadline()
                    + "\t,Label:" + task.getLabel());
        }
    }
}
      /* Iterator<Task> iter = tasks.iterator();
        while (iter.hasNext()) {
            Task task = iter.next();
            if (task.getName().equals(enter)) {
                iter.remove();
                return;
            }
        }
        */



