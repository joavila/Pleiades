package cl.borrego.process.batch;

import cl.borrego.process.exception.ErrorPresentTaskProcessorBusinessException;
import cl.borrego.process.exception.TaskProcessorBusinessException;
import cl.borrego.process.to.Task;
/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
public interface TaskProcessor {
    /**
     *
     * @param task
     * @return true if completed, false otherwise.
     * @throws ErrorPresentTaskProcessorBusinessException
     */
    boolean process ( Task task ) throws TaskProcessorBusinessException, ErrorPresentTaskProcessorBusinessException;
}
