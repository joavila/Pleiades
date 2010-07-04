package cl.borrego.process.exception;

import cl.borrego.foundation.exception.BusinessException;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 03-02-2010
 * Time: 09:00:26 PM
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 */
public class ErrorPresentTaskProcessorBusinessException extends BusinessException {
    public ErrorPresentTaskProcessorBusinessException(Throwable t) {
        super(t);
    }
}
