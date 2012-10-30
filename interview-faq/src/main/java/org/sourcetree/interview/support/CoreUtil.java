/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * CoreUtil.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Oct 30, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.support;

import java.util.Collection;
import java.util.Map;

/**
 * Core Utility for the whole platform
 * 
 * @author Venkaiah Chowdary Koneru
 * 
 */
public final class CoreUtil
{
	/**
	 * Constructor. Private to prevent unnecessary instantiation.
	 */
	private CoreUtil()
	{
	}

	/**
	 * This method returns true if the collection is null or is empty.
	 * 
	 * @param collection
	 * @return true | false
	 */
	public static boolean isEmpty(Collection<?> collection)
	{
		if (collection == null || collection.isEmpty())
		{
			return true;
		}
		return false;
	}

	/**
	 * This method returns true of the map is null or is empty.
	 * 
	 * @param map
	 * @return true | false
	 */
	public static boolean isEmpty(Map<?, ?> map)
	{
		if (map == null || map.isEmpty())
		{
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the input array is null or its length is
	 * zero.
	 * 
	 * @param array
	 * @return true | false
	 */
	public static boolean isEmpty(Object[] array)
	{
		if (array == null || array.length == 0)
		{
			return true;
		}
		return false;
	}
}
