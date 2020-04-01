package model.test;


import model.exceptions.InputInvalidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import model.Task;
import model.ToDoList;

import persistence.Reader;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    private static final String TODOLIST_FILE = "./data/testReader.txt";
    private ToDoList testList;
    private Task cpsc = new Task("cpsc");
    private Task math = new Task("math");


    @BeforeEach
    public void runBefore() {
        testList = new ToDoList();
        testList.addTask(cpsc);
        testList.addTask(math);


    }
    @Test
    void testReader(){
        new Reader();
    }

    @Test
    void testParseTask() {
        try {
            ToDoList toDoList2 = Reader.readTask(new File(TODOLIST_FILE));
            testList.addToDoList(toDoList2);
            Task task3 = toDoList2.getTaskPos(0);
            assertEquals("cpsc", task3.getName());
            assertEquals(1.01, task3.getDeadline());
            assertEquals("White", task3.getLabel());
            assertFalse(task3.getStatus());

            Task task4 = toDoList2.getTaskPos(1);
            assertEquals("math", task4.getName());
            assertEquals(1.01, task4.getDeadline());
            assertEquals("none", task4.getLabel());
            assertTrue(task4.getStatus());

        } catch (IOException | InputInvalidException e) {
            fail("IOException should not have been thrown");
        }


    }

    @Test
    void testIOExpection() {
        try {
            Reader.readTask(new File("/Users/Sammy/Documents/CPSC210/PersonalProject/MyApp/data/myFile.txt"));

        } catch (IOException | InputInvalidException e) {
            //
        }
    }




}