
package model;

// Represents a task that has a name,deadline,label and status

import model.exceptions.InputInvalidException;

import java.math.BigDecimal;

public class Task {
    private String name; // name of task
    private double deadline; // the task deadline
    private String label; // give task a category
    private boolean status; // the status of completion of task

    /* REQUIRES: name has a non-zero length;
     * EFFECTS: name on task is set to name, deadline is zero as in not set,
     *          label is empty string, and the status is false as in incomplete;
     */
    public Task(String name) {
        this.name = name;
        this.deadline = 1.01;
        this.label = "none";
        this.status = false;
    }



    // MODIFIES: this
    // EFFECTS: set deadline of task to the given date, throws InputInvalidException
    // if input is invalid
    public Double addTaskDate(double dateNum) throws InputInvalidException {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(dateNum));
        int month = bigDecimal.intValue();

        BigDecimal bigDecimal1 = bigDecimal.subtract(new BigDecimal(month));
        BigDecimal dayBigDecimal = bigDecimal1.multiply(new BigDecimal(100));
        int day = dayBigDecimal.intValue();
        boolean validInput = checkValidInput(month, day);

        if (validInput) {

            deadline = dateNum;
            return deadline;

        } else {
            throw new InputInvalidException();
        }


    }

    //EFFECTS: return true if date input is valid, otherwise return false
    public boolean checkValidInput(int month, int day) {

        if (month > 12 || month < 1 || day > 31 || day < 1) {
            return false;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return day <= 30;
        } else if (month == 2) {
            return day <= 29;
        }

        return true;
    }

    // MODIFIES: this
    // EFFECTS: set label according to the given colour number
    public String addTaskLabel(String label) {
        this.label = label;
        return this.label;

    }

    // MODIFIES: this
    // EFFECTS: set status according to the given boolean value
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


