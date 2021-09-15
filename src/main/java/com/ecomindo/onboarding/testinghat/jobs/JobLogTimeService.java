package com.ecomindo.onboarding.testinghat.jobs;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class JobLogTimeService extends QuartzJobBean {
	private static final Logger logger = LoggerFactory.getLogger(JobLogTimeService.class);


	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {

			logger.info("Executing Job with key {}", jobExecutionContext.getJobDetail().getKey());
			logger.info("Time now : "+new Date());

		} catch (Exception e) {
			logger.error("Failed to do scheduled job", e);
			throw e;
		}
	}

}
