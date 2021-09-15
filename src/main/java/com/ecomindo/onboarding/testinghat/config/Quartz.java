package com.ecomindo.onboarding.testinghat.config;

import com.ecomindo.onboarding.testinghat.jobs.JobInsertFromFileService;
import com.ecomindo.onboarding.testinghat.jobs.JobLogTimeService;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Quartz {
	@Bean
	public JobDetail jobLogTimeDetails() {
		return JobBuilder.newJob(JobLogTimeService.class).withIdentity("JobLogTime", "log-time")
				.storeDurably()
				.build();
	}

	@Bean
	public Trigger jobLogTimeTrigger(JobDetail jobLogTimeDetails) {
		return TriggerBuilder.newTrigger().forJob(jobLogTimeDetails)
				.withIdentity("trigger-from-main", "log-time")
				.startNow()
				.withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * ? * * *")).build();
	}

    @Bean
	public JobDetail jobAddHatsFromFileDetails() {
		return JobBuilder.newJob(JobInsertFromFileService.class).withIdentity("JobAddHatsFromFile", "add-hats-from-file")
				.storeDurably()
				.build();
	}

	@Bean
	public Trigger jobAddHatsFromFileTrigger(JobDetail jobAddHatsFromFileDetails) {
		return TriggerBuilder.newTrigger().forJob(jobAddHatsFromFileDetails)
				.withIdentity("trigger-from-main", "add-hats-from-file")
				.startNow()
				.withSchedule(CronScheduleBuilder.cronSchedule("0 */2 * ? * * *")).build();
	}
	
}
