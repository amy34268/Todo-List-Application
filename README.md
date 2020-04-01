# My Personal Project

## todo list


The project I choose is **Todo List**, the application can help people keep track of things, 
either completed or to be completed.
it will include features such as adding and deleting tasks.

People such as business professionals or students would likely find this application helpful as they 
have to keep track of tasks and deadlines.

I choose this to do this project because I personally benefited from the useful functions todo list offer which
keep me updated of school work.I am interested to learn the logic of how such an application is built.

## User Stories

- *As a user, I want to be able to add a task to to-do list*
- *As a user, I want to be able to delete a task from my to-do list*
- *As a user, I want to be add a date to a task in the to-do list*
- *As a user, I want to be add a label to a task in the to-do list*
- *As a user, I want to be able to view the list of tasks on my to-do list*
- *As a user, I want to be able to save my to-do list to file*
- *As a user, I want to be able to load my to-do list from file when the program starts*

##Instructions for Grader 
- Access main through ToDoListGUI

- You can generate the first required event by enter words in the filed next to add button and 
click add. For example: enter task name as "task"

- You can generate the second required event by selecting the "task" you entered on the panel 
above, click it and press ENTER on keyboard, a pop up window should show the label/deadline/status

(label can be entered by typing words such as "cpsc 210 " and click the "label" button 
 date may be entered by typing in the format of "3.22", and click the "date" button 
,status of the task can be set as complete through clicking the "complete" button)

- You can locate my visual component by doing the step above, selecting a task and press the enter key
should show a pop up window with a visual on the left 

- You can save the state of my application by clicking the save button and exit the application 

- You can reload the state of my application by rerunning the application and to click load button to load task


#Phase 4: Task 2 
I have chosen the first option: test and design a class that is robust.

In **Task** Class, **addTaskDate** method will identify if the dateInput in the format **month.date (##.##)** in 
ToDoListGUI is a valid date(Month: 1-12, Date: 1-31 (Different for each month)) .If not, it will throw
 **InputInvalidException**,and **ToDoListGUI's dateCommandWithException** method will catch it. When it catches
 the exception, a message box will
display "the date you entered is incorrect".

#Phase 4: Task 3

- Writer class and ToDoList class should not have separated DELIMITER, they should be the same value at all time

   Improvement: import static persistence.Reader.DELIMITER in toDoList class, so the change in the constant in Writer
   will also reflect on ToDoList
   
-  When setting UI elements suc has JButtons ("ADD") and setActionCommand("ADD"), the String "ADD" 
should be the same value at all time

- functionality for the UI elements can be extracted from GUI and make them into a new Class.
 
- Save and Load Functions can be separated from GUI and make them into a new Class.
