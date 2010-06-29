package cl.borrego.process.batch.impl;

import cl.borrego.foundation.system.Properties;
import cl.borrego.foundation.util.Message;
import cl.borrego.process.batch.TaskLifeCycleManager;
import cl.borrego.process.batch.TaskProcessor;
import cl.borrego.process.exception.ErrorPresentTaskProcessorBusinessException;
import cl.borrego.process.exception.TaskLifeCycleManagerBusinessException;
import cl.borrego.process.exception.TaskProcessorBusinessException;
import cl.borrego.process.to.PoisonousTask;
import cl.borrego.process.to.Report;
import cl.borrego.process.to.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
public class TaskLifeCycleManagerImpl implements TaskLifeCycleManager {
    private TaskProcessor m_taskProcessor;
    private List<Report> m_processReportList = new ArrayList<Report>();
    private static final Log LOG = LogFactory.getLog(TaskLifeCycleManagerImpl.class.getName());

    public TaskLifeCycleManagerImpl(TaskProcessor taskProcessor) {
        m_taskProcessor = taskProcessor;
    }

    /**
     * Process the task. Acceptable end states are Completed, Error or Initial state.
     *
     * @param taskQueue Distribute tasks
     * @return ProcessReport All processed reports
     */
    public synchronized List<Report> processTask(Queue<Task> taskQueue) {
        if (taskQueue == null) {
            throw new RuntimeException();
        }
        for (; ;) {
            Task task = taskQueue.poll();
            try {
                if (task == null) {
                    //TODO should it wait some time?
                } else {
                    if (task instanceof PoisonousTask) {
                        dumpFormattedMessage(Message.LevelEnum.INFO, "ending.process");
                        break;
                    }
                    successTaskLifeCyclePath(task);
                }
            } catch (ErrorPresentTaskProcessorBusinessException e) {
                dumpFormattedMessage(Message.LevelEnum.ERROR, "task.error.present", e);
                task.markWithError();
                dumpFormattedMessage(Message.LevelEnum.INFO, "marked.task.with.error", e);
            } catch (Exception e) {
                LOG.error(e);
                dumpFormattedMessage(Message.LevelEnum.ERROR, "process.error.present", e);
                task.release();
                dumpFormattedMessage(Message.LevelEnum.INFO, "released.task", e);
            } finally {
                //TODO assert status is a valid state
            }
        }
        dumpFormattedMessage(Message.LevelEnum.INFO, "processed.entries", m_processReportList.size());
        dumpFormattedMessage(Message.LevelEnum.DEBUG, "processed.entries.detail", m_processReportList);
        return m_processReportList;
    }

    private void successTaskLifeCyclePath(Task task) throws TaskLifeCycleManagerBusinessException, ErrorPresentTaskProcessorBusinessException {
        //acquire/reserve object
        dumpFormattedMessage(Message.LevelEnum.INFO, "tryingTo.acquireTask", task);
        task.acquire(m_processReportList);
        //if acquired process it
        dumpFormattedMessage(Message.LevelEnum.INFO, "tryingTo.process.task", task);
        try {
            getTaskProcessor().process(task);
        } catch (TaskProcessorBusinessException e) {
            throw new TaskLifeCycleManagerBusinessException(e);
        }
        //if process with no exception then mark as complete
        task.markAsComplete();
        dumpFormattedMessage(Message.LevelEnum.INFO, "process.task.completed", task);
    }

    public TaskProcessor getTaskProcessor() {
        return m_taskProcessor;
    }

    public void setTaskProcessor(TaskProcessor taskProcessor) {
        m_taskProcessor = taskProcessor;
    }

    private void dumpFormattedMessage(Message.LevelEnum level, String key, Object... arguments) {
        new Message(Properties.APP_MESSAGES_RESOURCE_BUNDLE).dumpFormattedMessage(TaskLifeCycleManagerImpl.class, level, key, arguments);
    }
}
