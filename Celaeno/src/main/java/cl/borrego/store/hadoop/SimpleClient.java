package cl.borrego.store.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 05-04-2010
 * Time: 09:39:12 PM
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
public class SimpleClient {
    public void main() {
        // Create a new JobConf
        JobConf job = new JobConf(new Configuration(), SimpleClient.class);

        // Specify various job-specific parameters
        job.setJobName("myjob");
        // Submit the job, then poll for progress until the job is complete
        try {
            JobClient.runJob(job);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
