
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
    public Task(String name) {
        this.name = name;
        this.deadline = 0;
        this.label = "none";
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
    public String addTaskLabel(String label) {
        this.label = label;
        return this.label;

    }

    public Boolean setStatus(Boolean status) {
        this.status = status;
        return status;
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

    public String getStringStatus() {

        String status = "not completed";
        if (this.status) {
            return "completed";
        }
        return status;
    }




}


