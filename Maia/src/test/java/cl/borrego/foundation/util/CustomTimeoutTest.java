package cl.borrego.foundation.util;

import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 29-01-2010
 * Time: 11:02:11 PM
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
public class CustomTimeoutTest {
    @Test
    public void testReturn() throws CustomTimeoutException {
        Object result = new CustomTimeout(1,new Callable<Object>(){
            public Object call() throws Exception {
                return null;
            }
        }).processTask();
        assertNull( result );
    }

    @Test(expected = CustomTimeoutException.class)
    public void testTimeout() throws CustomTimeoutException {
        new CustomTimeout(1,new Callable<Object>(){
            public Object call() throws Exception {
                Thread.sleep(20 * 1000);
                return null;
            }
        }).processTask();
        fail();
    }
}
