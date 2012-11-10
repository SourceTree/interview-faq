/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * AbstractTestCase.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Oct 31, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.JDBCException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Venkaiah Chowdary Koneru
 * 
 */
public class UtilTestCase
{
	/**
	 * 
	 * @param datasource
	 * @throws Exception
	 */
	public static void setupContext(BasicDataSource datasource)
			throws Exception
	{
		// START - dump test dummy data into HSQLDB
		Connection connection = null;
		Statement statement = null;
		long lineNo = 0L;
		BufferedReader reader = null;

		try
		{
			Resource re = new ClassPathResource("test-data.sql");
			connection = datasource.getConnection();
			statement = connection.createStatement();

			File file = re.getFile();

			lineNo = 0L;
			reader = new BufferedReader(new FileReader(file));

			for (String sql = reader.readLine(); sql != null; sql = reader
					.readLine())
			{
				String trimmedSql;

				lineNo++;
				trimmedSql = sql.trim();
				if (trimmedSql.length() == 0 || trimmedSql.startsWith("--")
						|| trimmedSql.startsWith("//")
						|| trimmedSql.startsWith("/*"))
				{
					continue;
				}
				else
				{
					statement.executeUpdate(trimmedSql);
				}
			}
			if (reader != null)
			{
				reader.close();
			}
		}
		catch (SQLException e)
		{
			throw new JDBCException("line no " + lineNo, e);
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			if (connection != null)
			{
				connection.close();
			}
			if (reader != null)
			{
				reader.close();
			}
		}
		// FINISH - dump dummy test data
	}

	/**
	 * 
	 * @param datasource
	 * @param redisTemplate
	 * @throws Exception
	 */
	public static void afterTestMethod(BasicDataSource datasource,
			RedisTemplate<String, Object> redisTemplate) throws Exception
	{
		Connection connection = null;
		Statement statement = null;

		// Flush the redis DB
		if (redisTemplate != null)
		{
			try
			{
				redisTemplate.getConnectionFactory().getConnection().flushDb();
			}
			catch (Exception e)
			{
				// Do nothing if jedis connection exception occurs.
				redisTemplate.discard();
			}
		}

		try
		{
			// Flush the HSQL DB
			connection = datasource.getConnection();
			statement = connection.createStatement();
			statement.execute("TRUNCATE SCHEMA public AND COMMIT");
		}
		catch (SQLException e)
		{
			throw new JDBCException("", e);
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
			if (connection != null)
			{
				connection.close();
			}
		}
	}
}
