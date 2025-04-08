package br.com.treinaweb.springjobrunr.api.jobs.controllers;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

import org.jobrunr.scheduling.JobBuilder;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.RecurringJobBuilder;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Map<String, String> fireAndForget(@RequestParam String arg) {
        var job = JobBuilder.aJob()
            .withName("Sample Job - Simple Job With Arg")
            .withLabels("sample-job", "simple-job-with-arg", "fire-and-forget")
            .withDetails(() -> sampleJob.simpleJobWithArg(arg));
        var job1Id = jobScheduler.create(job);

        var job2Id = jobScheduler.enqueue(sampleJob::simpleJob);

        return Map.of(
            "job1Id", job1Id.toString(),
            "job2Id", job2Id.toString()
        );
    }

    @GetMapping("/scheduled")
    public Map<String, String> scheduled(@RequestParam String arg) {
        var job1Id = jobScheduler.schedule(
            Instant.now().plusSeconds(30), 
            () -> sampleJob.simpleJobWithArg(arg)
        );

        var job = JobBuilder.aJob()
            .scheduleIn(Duration.ofSeconds(30))
            .withName("Sample Job - Simple Job With Arg")
            .withLabels("sample-job", "simple-job-with-arg", "scheduled")
            .withDetails(() -> sampleJob.simpleJobWithArg(arg));
        var job2Id = jobScheduler.create(job);

        return Map.of(
            "job1Id", job1Id.toString(),
            "job2Id", job2Id.toString()
        );
    }

    @GetMapping("/recurring")
    public Map<String, String> recurring(@RequestParam String arg) {
        var job = RecurringJobBuilder.aRecurringJob()
            .withName("Simple Job - Recurring")
            .withLabels("sample-job", "simple-job-with-arg", "recurring")
            .withCron(Cron.every30seconds())
            .withDetails(() -> sampleJob.simpleJobWithArg(arg));
        var job1Id = jobScheduler.createRecurrently(job);

        var job2Id = jobScheduler.scheduleRecurrently(
            Cron.every30seconds(),
            () -> sampleJob.simpleJobWithArg(arg)
        );

        return Map.of("job1Id", job1Id, "job2Id", job2Id);
    }
    
}
