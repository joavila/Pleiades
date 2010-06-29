package cl.borrego.foundation.exception;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 28-01-2010
 * Time: 06:32:39 PM
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
import static org.junit.Assert.*;

public class BusinessExceptionTest {
    @org.junit.Test
    public void test(){
        assertNotNull(new BusinessException(null));
    }
}
