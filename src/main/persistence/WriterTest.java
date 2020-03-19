package persistence;

import model.ToDoList;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILES = "./data/testReader.txt";
    private Writer testWriter;
    private ToDoList taskList;
    private ToDoList taskList2;
    private Task testTask1 = new Task("test1");
    private Task testTask2 = new Task("test2");
    private Task testTask3 = new Task("test3");
    private Task testTask4 = new Task("test4");



    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILES));
        taskList = new ToDoList();
        taskList.addTask(testTask1);
        taskList.getTaskPos(0).addTaskLabel("White");
        taskList.addTask(testTask2);

    }

    @Test
    void testWriteAccounts() {
        // save multiple tasks to file
        ;
        testWriter.write(taskList);
        testWriter.close();

        // now read them back in and verify that the Member have the expected tasks
        try {
            ToDoList tasks = Reader.readTask(new File(TEST_FILES));
            assertEquals("test1",tasks.getTaskPos(0).getName());
            assertEquals("White",tasks.getTaskPos(0).getLabel());
            assertEquals("test2",tasks.getTaskPos(1).getName());
            assertEquals(tasks.getTaskPos(0).getDeadline(),0.0);

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}