package ui;

import model.Task;
import model.ToDoList;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

//To-Do List application
public class ToDoListApp {
    private ToDoList toDoList;
    private Scanner input;

    //EFFECTS: Run the to-do list application
    public ToDoListApp() {
        runToDoList();
    }

    //MODIFIES: this
    //EFFECTS: processes user input and initialize a to-do list
    private void runToDoList() {
        toDoList = new ToDoList();
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);


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
    private void processCommand(String command) {
        switch (command) {
            case "a":
                System.out.println("add task");
                doAddTask();
                break;
            case "d":
                System.out.println("delete task");
                doDeleteTask();
                break;
            case "date":
                System.out.println("add date");
                doAddTaskDate();
                break;
            case "l":
                System.out.println("add label");
                doAddTaskLabel();
                break;
            case "v":
                System.out.println("view tasks:");
                displayTasks();
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
        System.out.println("\tq-> quit");

    }


    //MODIFIES: this
    //EFFECTS: add user entry as a task to the to do list
    private void doAddTask() {
        System.out.println("Enter your task name:");
        String enter = input.next();
        Task x = new Task(enter);
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


