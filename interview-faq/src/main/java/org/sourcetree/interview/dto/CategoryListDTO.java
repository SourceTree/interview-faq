/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * CategoryListDTO.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * 11-Nov-2012			Venkaiah Chowdary Koneru						Created
 * *************************************************************
 */
package org.sourcetree.interview.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * List of categories with listing data.
 * 
 * @author Venkaiah Chowdary Koneru
 */
@XmlRootElement(name = "categories")
public class CategoryListDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<CategoryDTO> categoryDTOs;
	private ListProp listProp;

	/**
	 * @return the categoryDTOs
	 */
	public List<CategoryDTO> getCategoryDTOs()
	{
		return categoryDTOs;
	}

	/**
	 * @param categoryDTOs
	 *            the categoryDTOs to set
	 */
	public void setCategoryDTOs(List<CategoryDTO> categoryDTOs)
	{
		this.categoryDTOs = categoryDTOs;
	}

	/**
	 * @return the listProp
	 */
	public ListProp getListProp()
	{
		return listProp;
	}

	/**
	 * @param listProp
	 *            the listProp to set
	 */
	public void setListProp(ListProp listProp)
	{
		this.listProp = listProp;
	}

	/**
	 * for jaxb marshalling purpose
	 */
	public CategoryListDTO()
	{
	}

	/**
	 * parameterized constructor
	 * 
	 * @param categoryDTOs
	 * @param listProp
	 */
	public CategoryListDTO(List<CategoryDTO> categoryDTOs, ListProp listProp)
	{
		this.categoryDTOs = categoryDTOs;
		this.listProp = listProp;
	}
}
