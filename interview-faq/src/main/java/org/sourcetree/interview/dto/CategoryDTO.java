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

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;
import org.sourcetree.interview.entity.Question;

/**
 * @author Chalam Pavuluri
 */
@XmlRootElement(name = "category")
public class CategoryDTO extends BaseDTO
{
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "categoryName.null")
	private String categoryName;

	private String categoryDescription;

	private Set<Question> questions = new HashSet<Question>();

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
	 * @return the questions
	 */
	public Set<Question> getQuestions()
	{
		return questions;
	}

	/**
	 * @param questions
	 *            the questions to set
	 */
	public void setQuestions(Set<Question> questions)
	{
		this.questions = questions;
	}

}
