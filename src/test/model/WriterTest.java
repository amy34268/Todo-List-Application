package presistence;

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
    private Task testTask1 = new Task("test1", 0.0,"1",false);
    private Task testTask2 = new Task("test2", 0.1,"2",false);



    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILES));
        taskList = new ToDoList();

    }

    @Test
    void testWriteAccounts() {
        // save multiple tasks to file
        taskList.addTask(testTask1);
        taskList.addTask(testTask2);
        testWriter.write(taskList);
        testWriter.close();

        // now read them back in and verify that the Member have the expected tasks
        try {
            ToDoList tasks = Reader.readTask(new File(TEST_FILES));
            assertEquals(tasks.getTaskPos(0).getName(),"test1");
            assertEquals(tasks.getTaskPos(1).getName(),"test2");
            assertEquals(tasks.getTaskPos(0).getDeadline(),0.0);

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}