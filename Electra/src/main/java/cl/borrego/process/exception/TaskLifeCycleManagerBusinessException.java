package cl.borrego.process.exception;

import cl.borrego.foundation.exception.BusinessException;

import java.lang.Throwable;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
public class TaskLifeCycleManagerBusinessException extends BusinessException {
    public TaskLifeCycleManagerBusinessException( Throwable t ) {
        super( t );
    }
}
