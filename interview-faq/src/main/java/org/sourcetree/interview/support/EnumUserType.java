/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * EnumUserType.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 05, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.support;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.EnhancedUserType;
import org.hibernate.usertype.ParameterizedType;
import org.sourcetree.interview.AppConstants;

/**
 * Support for mapping Java enumerated types through Hibernate. See
 * http://www.hibernate.org/272.html for more information and discussion.
 * 
 * @author Gavin King
 * 
 *         and Modified By Venkaiah Chowdary Koneru to support hibernate 4.
 */
@SuppressWarnings(AppConstants.SUPPRESS_WARNINGS_UNCHECKED)
public class EnumUserType implements EnhancedUserType, ParameterizedType
{

	@SuppressWarnings(AppConstants.SUPPRESS_WARNINGS_RAWTYPES)
	private Class<Enum> enumClass;

	@SuppressWarnings(AppConstants.SUPPRESS_WARNINGS_RAWTYPES)
	@Override
	public void setParameterValues(Properties parameters)
	{
		String enumClassName = parameters.getProperty("enumClassName");
		try
		{
			enumClass = (Class<Enum>) Class.forName(enumClassName);
		}
		catch (ClassNotFoundException cnfe)
		{
			throw new HibernateException("Enum class not found", cnfe);
		}
	}

	@Override
	public Object assemble(Serializable cached, Object owner)
			throws HibernateException
	{
		return cached;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException
	{
		return value;
	}

	@SuppressWarnings(AppConstants.SUPPRESS_WARNINGS_RAWTYPES)
	@Override
	public Serializable disassemble(Object value) throws HibernateException
	{
		return (Enum) value;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException
	{
		if (x == null || y == null)
		{
			return false;
		}

		return x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException
	{
		return x.hashCode();
	}

	@Override
	public boolean isMutable()
	{
		return false;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor implementor, Object owner)
			throws HibernateException, SQLException
	{
		String name = rs.getString(names[0]);
		return rs.wasNull() ? null : Enum.valueOf(enumClass, name);
	}

	@SuppressWarnings(AppConstants.SUPPRESS_WARNINGS_RAWTYPES)
	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor implementor) throws HibernateException,
			SQLException
	{
		if (value == null)
		{
			st.setNull(index, Types.VARCHAR);
		}
		else
		{
			st.setString(index, ((Enum) value).name());
		}
	}

	@Override
	public Object replace(Object original, Object target, Object owner)
			throws HibernateException
	{
		return original;
	}

	@SuppressWarnings(AppConstants.SUPPRESS_WARNINGS_RAWTYPES)
	@Override
	public Class returnedClass()
	{
		return enumClass;
	}

	@Override
	public int[] sqlTypes()
	{
		return new int[] { Types.VARCHAR };
	}

	@Override
	public Object fromXMLString(String xmlValue)
	{
		return Enum.valueOf(enumClass, xmlValue);
	}

	@SuppressWarnings(AppConstants.SUPPRESS_WARNINGS_RAWTYPES)
	@Override
	public String objectToSQLString(Object value)
	{
		return '\'' + ((Enum) value).name() + '\'';
	}

	@SuppressWarnings(AppConstants.SUPPRESS_WARNINGS_RAWTYPES)
	@Override
	public String toXMLString(Object value)
	{
		return ((Enum) value).name();
	}
}
