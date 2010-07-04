package cl.borrego.process.batch;

import cl.borrego.process.to.Report;
import cl.borrego.process.to.Task;

import java.util.List;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 28-01-2010
 * Time: 11:54:19 PM
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 */
public interface TaskLifeCycleManager {
    public List<Report> processTask(Queue<Task> task);
}
