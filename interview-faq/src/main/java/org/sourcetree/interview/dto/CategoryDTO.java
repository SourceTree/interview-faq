/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * CategoryDTO.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 06, 2012		Chalam Pavuluri				Created
 * *************************************************************
 */
package org.sourcetree.interview.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Chalam Pavuluri
 */
@XmlRootElement(name = "category")
public class CategoryDTO extends BaseDTO
{
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "categoryName.null")
	private String categoryName;

	private String categoryDescription;

	private List<QuestionDTO> questionDtos;

	/**
	 * @return the categoryName
	 */
	public String getCategoryName()
	{
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}

	/**
	 * @return the categoryDescription
	 */
	public String getCategoryDescription()
	{
		return categoryDescription;
	}

	/**
	 * @param categoryDescription
	 *            the categoryDescription to set
	 */
	public void setCategoryDescription(String categoryDescription)
	{
		this.categoryDescription = categoryDescription;
	}

	/**
	 * @return the questionDtos
	 */
	public List<QuestionDTO> getQuestionDtos()
	{
		return questionDtos;
	}

	/**
	 * @param questionDtos
	 *            the questionDtos to set
	 */
	public void setQuestionDtos(List<QuestionDTO> questionDtos)
	{
		this.questionDtos = questionDtos;
	}

	/**
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

}
