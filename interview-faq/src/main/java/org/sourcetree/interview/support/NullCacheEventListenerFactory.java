/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * NullCacheEventListenerFactory.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Oct 30, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.support;

import java.util.Properties;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

/**
 * EhCache event listener factory for preventing caching of null values in cache
 * data store.
 * 
 * @author Venkaiah Chowdary Koneru
 */
public class NullCacheEventListenerFactory extends CacheEventListenerFactory
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CacheEventListener createCacheEventListener(
			final Properties properties)
	{
		return NullCacheEventListener.INSTANCE;
	}
}
