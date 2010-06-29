package cl.borrego.foundation.exception;

import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 28-01-2010
 * Time: 06:36:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersistenceExceptionTest {
    @org.junit.Test
    public void test(){
        assertNotNull(new PersistenceException());
    }
}
