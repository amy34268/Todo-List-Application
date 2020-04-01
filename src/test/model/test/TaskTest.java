package model.test;

import model.Task;
import model.exceptions.InputInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task testTask;
    private Task testTask2;

    @BeforeEach
    void runBefore() {
        testTask = new Task("homework");
    }

    @Test
    void testConstructor() {

        assertEquals("homework", testTask.getName());
        assertEquals(1.01, testTask.getDeadline());
        assertEquals("none", testTask.getLabel());
        assertFalse(testTask.getStatus());
    }

    @Test
    void testGetName() {
        testTask = new Task("volunteer");
        assertEquals("volunteer", testTask.getName());
    }

    @Test
    void testGetStatus() {
        testTask2 = new Task("volunteer");
        assertFalse(testTask2.getStatus());
    }


    @Test
    void testAddTaskDateExceptionNotExpected() {

        try{
        testTask.addTaskDate(2.13);

        }
        catch(InputInvalidException e){
            fail("Exception should not thrown");
        }
    }

    @Test
    void testAddTaskDateExceptionExpected() {

        try{
            testTask.addTaskDate(2.30);
            fail("Exception should have been thrown");
        }
        catch(InputInvalidException e){
            //
        }
    }

    @Test
    void testCheckValidInput(){
        int month;
        int day;
        boolean value;
        month = 3;
        day = 13;
        value = testTask.checkValidInput(month,day);
        assertTrue(value);
        month = 13;
        day = 13;
        value = testTask.checkValidInput(month,day);
        assertFalse(value);
        month = 5;
        day = 32;
        value = testTask.checkValidInput(month,day);
        assertFalse(value);
        month = 4;
        day = 30;
        value = testTask.checkValidInput(month,day);
        assertTrue(value);
        month = 4;
        day = 31;
        value = testTask.checkValidInput(month,day);
        assertFalse(value);

        month = 2;
        day = 29;
        value = testTask.checkValidInput(month,day);
        assertTrue(value);
        month = 2;
        day = 30;
        value = testTask.checkValidInput(month,day);
        assertFalse(value);

    }


    @Test
    void testAddTaskLabel() {


        assertEquals("black", testTask.addTaskLabel("black"));
        assertEquals("white", testTask.addTaskLabel("white"));
        assertEquals("", testTask.addTaskLabel(""));
    }

    @Test
    void testSetStatus() {

        assertFalse(testTask.getStatus());
        assertEquals("not completed", testTask.getStringStatus());
        assertTrue(testTask.setStatus(true));
        assertEquals("completed", testTask.getStringStatus());

    }
}