package br.com.treinaweb.springjobrunr.core.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SampleJob {
    
    private final Logger log = LoggerFactory.getLogger(SampleJob.class);

    public void simpleJob() {
        log.info("Exexuting a simple job");
    }

}
