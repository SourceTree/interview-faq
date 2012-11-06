/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * QuestionDTO.java
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
import org.sourcetree.interview.entity.Category;

/**
 * @author Chalam Pavuluri
 */
@XmlRootElement(name = "question")
public class QuestionDTO extends BaseDTO
{
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "question.null")
	private String question;

	private List<Category> categories;

	/**
	 * @return the question
	 */
	public String getQuestion()
	{
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(String question)
	{
		this.question = question;
	}

	/**
	 * @return the categories
	 */
	public List<Category> getCategories()
	{
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(List<Category> categories)
	{
		this.categories = categories;
	}

}
