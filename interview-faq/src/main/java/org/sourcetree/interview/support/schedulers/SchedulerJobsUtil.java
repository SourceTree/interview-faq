/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * SchedulerJobsUtil.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Dec 01, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.support.schedulers;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.sourcetree.interview.support.CoreUtil;

/**
 * Utility Class for Scheduler jobs. Contains static methods for job creation
 * and scheduling.
 * 
 * @author Venkaiah Chowdary Koneru
 */
public final class SchedulerJobsUtil
{
	private static final Log LOG = LogFactory.getLog(SchedulerJobsUtil.class);

	private static final String JOB_NAME_PREFIX = "JOB_";
	private static final String JOB_GROUP_PREFIX = "JOB_GROUP_";

	private static final String TRIGGER_NAME_PREFIX = "TRIG_";
	private static final String TRIGGER_GROUP_PREFIX = "TRIG_GROUP_";

	/**
	 * Schedules a cron job with the specified joba name and cron expression.
	 * 
	 * @param scheduler
	 *            must be the running state instance.
	 * @param jobName
	 *            name of the particular job
	 * @param clazz
	 *            Quartz Job class
	 * @param dataMap
	 *            additional job data map
	 * @param cronExpression
	 *            can not be empty
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public static void scheduleCronJob(Scheduler scheduler,
			final String jobName, Class<? extends AbstractJob> clazz,
			final Map<String, Object> dataMap, final String cronExpression)
			throws SchedulerException, ParseException
	{
		String jobNameLocal = getJobName(jobName);
		String jobGroupNameLocal = getJobGroupName(jobName);

		scheduler.unscheduleJob(jobNameLocal, jobGroupNameLocal);

		JobDetail jobDetail = new JobDetail(jobNameLocal, jobGroupNameLocal,
				clazz);

		if (!CoreUtil.isEmpty(dataMap))
		{
			JobDataMap jobDataMap = new JobDataMap(dataMap);
			jobDetail.setJobDataMap(jobDataMap);
		}

		CronTrigger cronTrigger = new CronTrigger(getTriggerName(jobName),
				getTriggerGroupName(jobName), jobNameLocal, jobGroupNameLocal,
				cronExpression);

		scheduler.scheduleJob(jobDetail, cronTrigger);

		LOG.debug("Scheduled a cron Job [" + jobName + "] with trigger "
				+ cronExpression);
	}

	/**
	 * @param scheduler
	 *            must be the running state instance.
	 * @param jobName
	 *            name of the particular job
	 * @param clazz
	 * @param dataMap
	 * @param delayTimeInMillis
	 *            delay in millis for start
	 * @param repeatInterval
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public static void scheduleSimpleJob(Scheduler scheduler,
			final String jobName, Class<AbstractJob> clazz,
			final Map<String, Object> dataMap, final long delayTimeInMillis,
			final long repeatInterval) throws SchedulerException,
			ParseException
	{
		String jobNameLocal = getJobName(jobName);
		String jobGroupNameLocal = getJobGroupName(jobName);

		scheduler.unscheduleJob(jobNameLocal, jobGroupNameLocal);

		JobDetail jobDetail = new JobDetail(jobNameLocal, jobGroupNameLocal,
				clazz);

		JobDataMap jobDataMap = new JobDataMap(dataMap);
		jobDetail.setJobDataMap(jobDataMap);

		Date triggerDate = new Date(System.currentTimeMillis()
				+ delayTimeInMillis);

		SimpleTrigger simpleTrigger = new SimpleTrigger(
				getTriggerName(jobName), getTriggerGroupName(jobName),
				triggerDate);
		simpleTrigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		simpleTrigger.setRepeatInterval(repeatInterval);

		scheduler.scheduleJob(jobDetail, simpleTrigger);

		LOG.debug("Scheduled a Simple Trigger Job [" + jobName + "] to run at "
				+ triggerDate);
	}

	/**
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param clazz
	 * @return
	 */
	public static JobDetail getJobDetail(final String jobName,
			final String jobGroupName, Class<AbstractJob> clazz)
	{
		return new JobDetail(jobName, jobGroupName, clazz);
	}

	/**
	 * @param jobName
	 * @return
	 */
	public static String getJobName(final String jobName)
	{
		return JOB_NAME_PREFIX + jobName;
	}

	/**
	 * @param jobName
	 * @return
	 */
	public static String getJobGroupName(final String jobName)
	{
		return JOB_GROUP_PREFIX + jobName;
	}

	/**
	 * @param jobName
	 * @return
	 */
	public static String getTriggerName(final String jobName)
	{
		return TRIGGER_NAME_PREFIX + jobName;
	}

	/**
	 * @param jobName
	 * @return
	 */
	public static String getTriggerGroupName(final String jobName)
	{
		return TRIGGER_GROUP_PREFIX + jobName;
	}
}
