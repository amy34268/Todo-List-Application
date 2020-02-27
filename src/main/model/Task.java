package model;

// Represents a task that has a name,deadline,label and status

public class Task {
    private String name; // name of task
    private double deadline; // the task deadline
    private String label; // give task a category
    private boolean status; // the status of completion of task

    /* REQUIRES: name has a non-zero length;
     * EFFECTS: name on task is set to name, deadline is zero as in not set,
     *          label has no value, and the status is false as in incomplete;
     */
    public Task(String name, Double deadline, String label, Boolean status) {
        this.name = name;

        this.deadline = deadline;
        this.deadline = 0;
        this.label = label;
        this.label = "";
        this.status = status;
        this.status = false;
    }


    // REQUIRES: date is a positive number
    // MODIFIES: this
    // EFFECTS: set deadline of task to the given date
    public Double addTaskDate(double date) {
        deadline = date;
        return deadline;
    }

    // MODIFIES: this
    // EFFECTS: set label according to the given colour number
    public String addTaskLabel(int colour) {

        if (colour == 1) {
            label = "black";
        } else if (colour == 2) {
            label = "white";
        } else {
            label = "";
        }
        return label;

    }

    public String getName() {
        return name;
    }

    public double getDeadline() {
        return deadline;
    }

    public String getLabel() {
        return label;
    }

    public boolean getStatus() {
        return status;
    }

}




