package cl.borrego.foundation.util;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 30-01-2010
 * Time: 03:58:20 PM
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
public class CustomTimeoutException extends Exception {
    public CustomTimeoutException(Throwable e) {
        super( e );
    }
}
