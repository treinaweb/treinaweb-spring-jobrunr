package br.com.treinaweb.springjobrunr.api.jobs.controllers;

import java.util.Map;

import org.jobrunr.scheduling.JobScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.springjobrunr.core.jobs.SampleJob;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobs")
public class JobRestController {

    private final SampleJob sampleJob;
    private final JobScheduler jobScheduler;

    @GetMapping("/fire-and-forget")
    public Map<String, String> fireAndForget() {
        var jobId = jobScheduler.enqueue(sampleJob::simpleJob);
        return Map.of("jobId", jobId.toString());
    }
    
}
