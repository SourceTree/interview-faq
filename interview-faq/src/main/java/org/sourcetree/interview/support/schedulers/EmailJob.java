/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * EmailJob.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Dec 02, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.support.schedulers;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.sourcetree.interview.support.CoreUtil;

/**
 * Email Job.
 * 
 * @author Venkaiah Chowdary Koneru
 */
public class EmailJob extends AbstractJob
{
	private static final Log LOG = LogFactory.getLog(EmailJob.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException
	{
		LOG.debug("Email Job has been triggered.");

		JobDataMap jobDataMap = jobExecutionContext.getJobDetail()
				.getJobDataMap();

		Map<String, String> emailConfig = ((JobsInitializerBean) jobDataMap
				.get(JobsInitializerBean.class.getSimpleName()))
				.getEmailConfig();

		if (!CoreUtil.isEmpty(emailConfig))
		{

		}

		LOG.debug("Email Job has been successfully completed.");
	}
}
