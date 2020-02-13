package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ToDoListApp;


import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoListTest {

    ToDoList todo;
    ToDoList todo1;
    Task cpsc;
    Task math;
    Task volunteer;


    @BeforeEach
    void runBefore() {
        todo = new ToDoList();
        cpsc = new Task("cpsc");
        math = new Task("math");
        volunteer = new Task("volunteer");
    }

    @Test
    void testConstructor() {
        todo1 = new ToDoList();
        assertEquals(0, todo1.getNumToDoList());

    }


    @Test
    void testAddTask(){
        todo.addTask(cpsc);
        assertEquals(cpsc, todo.getTaskPos(0));
        assertEquals(1,todo.getNumToDoList());
        todo.addTask(math);
        assertEquals(cpsc, todo.getTaskPos(0));
        assertEquals(math, todo.getTaskPos(1));
        assertEquals(2,todo.getNumToDoList());
    }

    @Test
    void testDeleteTask(){
        todo.addTask(cpsc);
        todo.addTask(math);
        todo.deleteTask(cpsc);
        assertEquals(math, todo.getTaskPos(0));
        assertEquals(1,todo.getNumToDoList());

    }
    @Test
    void testGetToDoList(){
        todo.addTask(cpsc);
        todo.addTask(math);
        assertEquals (cpsc, todo.getToDoList().get(0));
        assertEquals (math, todo.getToDoList().get(1));
    }






}