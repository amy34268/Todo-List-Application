package model;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import model.Task;
import model.ToDoList;

import persistence.Reader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    private static final String TODOLIST_FILE = "./data/testReader.txt";
    private Reader reader;
    private ToDoList toDolist;
    private Task testTask1 = new Task("test1", 0.0,"",false);
    private Task testTask2 = new Task("test2", 0.0,"",false);



    @BeforeEach
    public void runBefore(){
        toDolist= new ToDoList();
        toDolist.addTask(testTask1);
        toDolist.addTask(testTask2);
    }
    @Test
    void testParseTask() {
        assertEquals(2,toDolist.getNumToDoList());

        try {
            ToDoList toDoList2 = Reader.readTask(new File (TODOLIST_FILE));
            toDolist.addToDoList(toDoList2);
            Task testTask3 = toDoList2.getTaskPos(0);
            assertEquals("test1", testTask3.getName());
            Task testTask4 = toDoList2.getTaskPos(1);
            assertEquals("test2", testTask3.getName());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOExpection() {
        try {
           Reader.readTask(new File("/Users/Sammy/Documents/CPSC210/PersonalProject/MyApp/data/myFile.txt"));

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }


}