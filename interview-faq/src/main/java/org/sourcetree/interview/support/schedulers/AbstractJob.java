/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * AbstractJob.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Dec 01, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.support.schedulers;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

/**
 * Abstract Job class. All schedulers must extend this class.
 * 
 * @author Venkaiah Chowdary Koneru
 */
public abstract class AbstractJob implements StatefulJob
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract void execute(JobExecutionContext arg0)
			throws JobExecutionException;

}
