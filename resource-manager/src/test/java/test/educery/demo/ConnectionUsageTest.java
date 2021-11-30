package test.educery.demo;

import java.io.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import dev.educery.demo.ExampleMain;

/**
 * Confirms proper operation of connection usage.
 * @author Nik Boyd <nik.boyd@educery.dev>
 * @see "Copyright 2001,2020 Nikolas S. Boyd"
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ExampleMain.class })
public class ConnectionUsageTest { // wraps main in a test
    @Test public void testConnection() { ExampleMain.main(); }
    static {
        // ensure test database from prior test run is removed first
        File dbFile = new File("target/test.mv.db");
        if (dbFile.exists()) dbFile.delete();
    }

} // ConnectionUsageTest
