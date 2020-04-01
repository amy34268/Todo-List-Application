package model.test;

import model.ToDoList;
import model.Task;
import model.exceptions.InputInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILES = "./data/testReader.txt";
    private Writer testWriter;
    private ToDoList testlist;
    private Task cpsc = new Task("cpsc");
    private Task math = new Task("math");



    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILES));
        testlist = new ToDoList();
        testlist.addTask(cpsc);
        testlist.getTaskPos(0).addTaskLabel("White");
        testlist.addTask(math);
        testlist.getTaskPos(1).setStatus(true);


    }

    @Test
    void testWrite() {
        // save multiple tasks to file

        testWriter.write(testlist);
        testWriter.close();

        // now read them back in and verify that the Member have the expected tasks
        try {
            ToDoList tasks = Reader.readTask(new File(TEST_FILES));
            assertEquals("cpsc", tasks.getTaskPos(0).getName());
            assertEquals("White", tasks.getTaskPos(0).getLabel());
            assertEquals( 1.01,tasks.getTaskPos(0).getDeadline());
            assertEquals("math", tasks.getTaskPos(1).getName());
            assertEquals("completed", tasks.getTaskPos(1).getStringStatus());

        } catch (IOException | InputInvalidException e) {
            fail("IOException should not have been thrown");
        }
    }


}
