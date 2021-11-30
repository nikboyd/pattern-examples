package test.educery.resources;

import org.junit.*;
import dev.educery.resources.*;
import static java.lang.String.*;

/**
 * Confirms proper operation of resource storage.
 * @author Nik Boyd <nik.boyd@educery.dev>
 * @author Copyright 2003,2020 Nikolas S. Boyd
 */
public class ResourceTest implements Reporting {

    static final String ResourceReport = "%s %s";
    @Test public void testResource() {
        Resource aaa = Resource.named("aaa");
        Resource bbb = Resource.named("bbb");
        report(format(ResourceReport, aaa.getValue(), bbb.getValue()));

        String excited = "excitedly ";
        if (!bbb.getValue().startsWith(excited)) {
            bbb.setValue(excited + bbb.getValue());
        }
    }

} // ResourceTest
