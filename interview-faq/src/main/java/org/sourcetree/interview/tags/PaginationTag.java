/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * PaginationTag.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Dec 1, 2012			chalam						Created
 * *************************************************************
 */
package org.sourcetree.interview.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.sourcetree.interview.dto.ListProp;

/**
 * @author chalam
 * 
 */
public class PaginationTag extends TagSupport
{

	private ListProp listProp;

	private String searchValue;

	private String urlPrefix;

	/**
	 * @param listProp
	 *            the listProp to set
	 */
	public void setListProp(ListProp listProp)
	{
		this.listProp = listProp;
	}

	/**
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(String searchValue)
	{
		this.searchValue = searchValue;
	}

	/**
	 * @param urlPrefix
	 *            the urlPrefix to set
	 */
	public void setUrlPrefix(String urlPrefix)
	{
		this.urlPrefix = urlPrefix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException
	{
		StringBuilder sb = new StringBuilder();

		sb.append("<div class='pagination'>");
		sb.append("<span class='pagination_total'>Total Records :");
		sb.append(listProp.getTotalRecords());
		sb.append(" </span>");
		sb.append("<span class='pagination_page'> Page:");
		sb.append(listProp.getPage());
		sb.append(" of ");
		sb.append(listProp.getTotalPages());
		sb.append(" </span>");
		sb.append("<br/>");

		if (listProp.getPage() > 1)
		{
			sb.append("<a class='pagerPrev pagination_total' href=");
			sb.append(urlPrefix);
			sb.append(listProp.getPage() - 1);
			sb.append("?searchValue=");
			sb.append(searchValue);
			sb.append(">");
			sb.append("<< PREV");
			sb.append("</a>");

		}
		if (listProp.getPage() != listProp.getTotalPages())
		{
			sb.append("<a class='pager pagination_page' href=");
			sb.append(urlPrefix);
			sb.append(listProp.getPage() + 1);
			sb.append("?searchValue=");
			sb.append(searchValue);
			sb.append(">");
			sb.append("NEXT >>");
			sb.append("</a>");
		}
		sb.append("</div>");

		try
		{
			pageContext.getOut().write(sb.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return SKIP_BODY;
	}
}
