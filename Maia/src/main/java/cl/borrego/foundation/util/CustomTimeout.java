package cl.borrego.foundation.util;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 29-01-2010
 * Time: 10:33:43 PM
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
public class CustomTimeout {
    private int m_timeout = -1;
    private Callable<Object> m_task;

    public CustomTimeout(int timeout, Callable<Object> task) {
        m_timeout = timeout;
        m_task = task;
    }

    public Object processTask() throws CustomTimeoutException {
        try {
            // create new task
            FutureTask<?> theTask = new FutureTask<Object>(getTask());

            // start task in a new thread
            new Thread(theTask).start();

            // wait for the execution to finish, timeout after 10 secs
            return theTask.get(getTimeout(), TimeUnit.SECONDS);
        }
        catch(InterruptedException e) {
            //handle interruption
            throw new CustomTimeoutException(e);
        }
        catch(ExecutionException e) {
            //handle exception
            throw new CustomTimeoutException(e);
        }
        catch(TimeoutException e){
            // handle timeout
            throw new CustomTimeoutException(e);
        }
    }

    public int getTimeout() {
        return m_timeout;
    }

    public void setTimeout(int timeout) {
        m_timeout = timeout;
    }

    public Callable<Object> getTask() {
        return m_task;
    }

    public void setTask(Callable<Object> task) {
        m_task = task;
    }
}
