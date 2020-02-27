package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task testTask;
    private Task testTask2;
    @BeforeEach
    void runBefore(){ testTask = new Task("homework",0.0,"",false);}

    @Test
    void testConstructor(){

        assertEquals("homework", testTask.getName());
        assertEquals( 0,testTask.getDeadline());
        assertEquals( "",testTask.getLabel());
        assertFalse(testTask.getStatus());
    }

    @Test
    void testGetName(){
        testTask = new Task("volunteer",0.0,"",false);
        assertEquals("volunteer", testTask.getName());
    }

    @Test
    void testGetStatus(){
        testTask2 = new Task("volunteer",0.0,"",false);
        assertFalse(testTask2.getStatus());
    }


    @Test
    void testAddTaskDate( ){

        assertEquals(2.13, testTask.addTaskDate(2.13));
    }

    @Test
    void testAddTaskLabel( ){


        assertEquals("black", testTask.addTaskLabel(1));
        assertEquals("white", testTask.addTaskLabel(2));
        assertEquals("", testTask.addTaskLabel(3));
    }



}