package cl.borrego.foundation.encoding;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 02-Jul-2010
 * Time: 16:17:11
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
public class EncodingTest {
    @Test
    public void test() {
        byte[] byteSecuence ;
        byteSecuence = new byte[]{(byte) 0xC3, (byte) 0xB1};
        byteSecuence = new byte[]{(byte) 0xC3, (byte) 0xB3};
        byteSecuence = new byte[]{(byte) 0xC2, (byte) 0xBF};
        byteSecuence = new byte[]{(byte) 0xBF};
        
        String string = null;

        for (String s : new String[]{"UTF-8", "ISO-8859-1"}) {
            try {
                string = new String(byteSecuence, s);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                Assert.fail();
            }
            Assert.assertNotNull(string);
            System.out.println("" + string);
        }

    }
}
