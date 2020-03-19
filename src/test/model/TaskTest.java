package model;

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
        assertEquals(0, testTask.getDeadline());
        assertEquals("", testTask.getLabel());
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
    void testAddTaskDate() {

        assertEquals(2.13, testTask.addTaskDate(2.13));
    }

    @Test
    void testAddTaskLabel() {


        assertEquals("black", testTask.addTaskLabel("black"));
        assertEquals("white", testTask.addTaskLabel("white"));
        assertEquals("", testTask.addTaskLabel(""));
    }


}