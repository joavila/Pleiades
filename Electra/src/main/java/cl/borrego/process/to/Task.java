package cl.borrego.process.to;

import cl.borrego.process.exception.TaskLifeCycleManagerBusinessException;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 */
public class Task extends Report {
    private int m_id;

    public Task(int id) {
        this.m_id = id;
        setState(State.INITIAL);
    }

    public int getId() {
        return this.m_id;
    }

    public void acquire(List<Report> processReportList) throws TaskLifeCycleManagerBusinessException {
        processReportList.add(this);
        try {
            if (isProcessAlreadyStarted()) {//start date different from null
                continueProcessingTask();
            } else {//start date is null
                startProcessingTask();
            }
        } catch (Exception ue) {
            throw new TaskLifeCycleManagerBusinessException(ue);
        }
        setState(State.STARTED);
    }

    public synchronized void markAsComplete() {
        setState(State.COMPLETED);
    }

    public void markWithError() {
        setState(State.ERROR);
    }

    public boolean release() {
        return false;
    }

    protected boolean processHasAnyErrors() {
        return false;
    }

    protected boolean isProcessAlreadyStarted() {
        return false;
    }

    protected boolean continueProcessingTask() {
        return false;
    }

    protected boolean startProcessingTask() {
        return false;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
