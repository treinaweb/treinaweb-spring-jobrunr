package br.com.treinaweb.springjobrunr.core.jobs;

import org.jobrunr.jobs.annotations.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SampleJob {
    
    private final Logger log = LoggerFactory.getLogger(SampleJob.class);

    @Job(name = "Sample Job - Simple Job", labels = { "smaple-job", "simple-job", "fire-and-forget" })
    public void simpleJob() {
        log.info("Executing a simple job");
    }

    public void simpleJobWithArg(String arg) {
        log.info("Executing a simple job with arg: " + arg);
    }

}
