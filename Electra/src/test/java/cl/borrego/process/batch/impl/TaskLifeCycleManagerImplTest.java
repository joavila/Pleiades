package cl.borrego.process.batch.impl;

import cl.borrego.foundation.util.Message;
import cl.borrego.process.batch.TaskLifeCycleManager;
import cl.borrego.process.batch.TaskProcessor;
import cl.borrego.process.exception.TaskProcessorBusinessException;
import cl.borrego.process.to.PoisonousTask;
import cl.borrego.process.to.Report;
import cl.borrego.process.to.Task;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 */
public class TaskLifeCycleManagerImplTest
        extends TestCase {
    private static final Message MESSAGE = new Message("testMessages");

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TaskLifeCycleManagerImplTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(TaskLifeCycleManagerImplTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testTaskProcessor() {
        TaskLifeCycleManager aTaskLifeCycleManager = new TaskLifeCycleManagerImpl(new TaskProcessor() {
            @Override
            public boolean process(Task task) {
                return false;
            }
        });
        /*
        aTaskLifeCycleManager = (TaskLifeCycleManager) cl.borrego.foundation.proxy.ChronometerProxy.newInstance(aTaskLifeCycleManager);
        //*/
        Queue<Task> queue = new LinkedList<Task>();
        queue.add(new Task(0));
        queue.add(new PoisonousTask());
        final List<Report> reports = aTaskLifeCycleManager.processTask(queue);
        assertNotNull(reports);
        assertFalse(reports.isEmpty());
        assertEquals(1, reports.size());
    }

    public void testConcurrentV1() {
        final List<Task> taskList = Arrays.asList(new Task(1), new Task(2), new Task(3), new Task(4), new Task(5));
        for (Task task : taskList) {
            MESSAGE.dumpFormattedMessage(TaskLifeCycleManagerImplTest.class, Message.LevelEnum.INFO, "tryingTo.process.task", task);
        }
        final Queue<Task> tasks = new LinkedBlockingQueue<Task>(taskList);

        for (int i = 0; i < taskList.size(); i++) {
            tasks.add(new PoisonousTask(i));
        }

        final ExecutorService pool = Executors.newFixedThreadPool(tasks.size());
        List<Callable<Object>> inputList = new ArrayList<Callable<Object>>();
        for (final Task task : taskList) {
            MESSAGE.dumpFormattedMessage(TaskProcessor.class, Message.LevelEnum.INFO, "prepartingTo.process.task", task);
            Callable<Object> callable =
                    new Callable<Object>() {
                        public Object call() throws Exception {
                            /*  Doesn't work this way      */
                            TaskLifeCycleManager aTaskLifeCycleManager = new TaskLifeCycleManagerImpl(new TaskProcessor() {
                                @Override
                                public /*synchronized*/ boolean process(Task task) {
                                    MESSAGE.dumpFormattedMessage(TaskProcessor.class, Message.LevelEnum.INFO, "tryingTo.process.task", task);
                                    /*
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                                        throw new TaskProcessorBusinessException(e);
                                    }
                                    //*/
                                    return false;  //To change body of implemented methods use File | Settings | File Templates.
                                }
                            });
                            //*/
                            return aTaskLifeCycleManager.processTask(tasks);
                        }
                    };
//            pool.submit(callable);
            inputList.add(callable);
        }
        assertFalse(inputList.isEmpty());
        MESSAGE.dumpFormattedMessage(TaskProcessor.class, Message.LevelEnum.INFO, "test.input.size", inputList.size());
        try {
            List<Future<Object>> outputList = pool.invokeAll(inputList);
            pool.shutdown();
//            assertTrue(pool.awaitTermination(100, TimeUnit.SECONDS));
            assertNotNull(outputList);
            assertFalse(outputList.isEmpty());
            List<Report> list = new ArrayList<Report>();
            for (Future<Object> future : outputList) {
                List<Report> resultList = (List<Report>)future.get();
                assertNotNull(resultList);
//                assertFalse(resultList.isEmpty());
                if (!resultList.isEmpty()) {
                    for (Report report : resultList) {
                        assertNotNull(report);
                        assertFalse(list.contains(report));
                        list.add(report);
                        final int resultId = report.getId();
                        assertTrue("Result id: " + resultId, resultId > 0);
                        assertEquals(Report.State.COMPLETED, report.getState());
                    }
                }
            }
            assertFalse(taskList.isEmpty());
            for (Task task : taskList) {
                MESSAGE.dumpFormattedMessage(TaskLifeCycleManagerImplTest.class, Message.LevelEnum.INFO, "processed.task", task);
                assertTrue("Task: " + task + " is not on the list: " + list, list.contains(task));
            }
        } catch (InterruptedException e) {
            fail(String.valueOf(e));
        } catch (ExecutionException e) {
            fail(String.valueOf(e));
        } catch (Exception e) {
            fail(String.valueOf(e));
        }
    }

    public void testConcurrentV2() {
        final List<Task> taskList = Arrays.asList(new Task(1), new Task(2), new Task(3), new Task(4), new Task(5));
        for (Task task : taskList) {
            MESSAGE.dumpFormattedMessage(TaskLifeCycleManagerImplTest.class, Message.LevelEnum.INFO, "tryingTo.process.task", task);
        }
        final Queue<Task> tasks = new LinkedBlockingQueue<Task>(taskList);
        tasks.add(new PoisonousTask());
        final ExecutorService pool = Executors.newFixedThreadPool(tasks.size());
        final TaskLifeCycleManager aTaskLifeCycleManager = new TaskLifeCycleManagerImpl(new TaskProcessor() {
            @Override
            public boolean process(Task task) throws TaskProcessorBusinessException {
                MESSAGE.dumpFormattedMessage(TaskProcessor.class, Message.LevelEnum.INFO, "tryingTo.process.task", task);
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        Callable<Object> callable =
                new Callable<Object>() {
                    public Object call() throws Exception {
                        /*  Doesn't work this way
                        TaskLifeCycleManager aTaskLifeCycleManager = new TaskLifeCycleManagerImpl(new TaskProcessor() {
                            @Override
                            public synchronized boolean process(Task task) throws TaskProcessorBusinessException {
                                MESSAGE.dumpFormattedMessage(TaskProcessor.class, Message.LevelEnum.INFO, "tryingTo.process.task", task);
                                return false;  //To change body of implemented methods use File | Settings | File Templates.
                            }
                        });
                        //*/
                        return aTaskLifeCycleManager.processTask(tasks);
                    }
                };
        try {
            Future future = pool.submit(callable);
            pool.shutdown();
//            assertTrue(pool.awaitTermination(100, TimeUnit.SECONDS));
            List<Report> list = new ArrayList<Report>();
            List<Report> resultList = (List<Report>) future.get();
            assertNotNull(resultList);
            assertFalse(resultList.isEmpty());
            for (Report report : resultList) {
                assertNotNull(report);
                assertFalse(list.contains(report));
                list.add(report);
                final int resultId = report.getId();
                assertTrue("Result id: " + resultId, resultId > 0);
                assertEquals(Report.State.COMPLETED, report.getState());
            }
            assertFalse(taskList.isEmpty());
            for (Task task : taskList) {
                MESSAGE.dumpFormattedMessage(TaskLifeCycleManagerImplTest.class, Message.LevelEnum.INFO, "processed.task", task);
                assertTrue("Task: " + task + " is not on the list: " + list, list.contains(task));
            }
        } catch (InterruptedException e) {
            fail(String.valueOf(e));
        } catch (ExecutionException e) {
            fail(String.valueOf(e));
        } catch (Exception e) {
            fail(String.valueOf(e));
        }
    }
}
