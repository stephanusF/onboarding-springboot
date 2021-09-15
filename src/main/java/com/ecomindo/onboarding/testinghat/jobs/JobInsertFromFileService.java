package com.ecomindo.onboarding.testinghat.jobs;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import com.ecomindo.onboarding.testinghat.config.Config;
import com.ecomindo.onboarding.testinghat.services.FileService;
import com.ecomindo.onboarding.testinghat.services.HatsService;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class JobInsertFromFileService extends QuartzJobBean {
	private static final Logger logger = LoggerFactory.getLogger(JobInsertFromFileService.class);

	@Autowired
	HatsService hatsService;

    @Autowired
	FileService fileService;

    @Autowired
	Config config;

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {

			logger.info("Executing Add Hats From File Job");

            List<String> content = fileService.getFileContent(config.getSftpFilename());

            // Future<Void> first = hatsService.addHatFromFileContent2(new ArrayList<>(content.subList(0, (content.size()) / 2)));
            // Future<Void> second = hatsService.addHatFromFileContent2(new ArrayList<>(content.subList((content.size()) / 2, content.size())));
            
            // while (!(first.isDone() && second.isDone())) {
            //     if(!first.isDone()){
            //         logger.info("Add Hats From File Job first batch is running...");
            //     }

            //     if(!second.isDone()){
            //         logger.info("Add Hats From File Job second batch is running...");
            //     }
			    
			//     Thread.sleep(500);
			// }

            hatsService.addHatFromFileContent(content);

            logger.info("Add Hats From File Job stopped");
		} catch (Exception e) {
			logger.error("Failed to add hats", e);
			//throw e;
		}
	}

}
