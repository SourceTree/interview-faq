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

/**
 * @author Chalam Pavuluri
 */
@XmlRootElement(name = "question")
public class QuestionDTO extends BaseDTO
{
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "question.null")
	private String question;

	private List<CategoryDTO> categoryDTOs;

	@NotEmpty(message = "answer.null")
	private String answer;

	private String categories[];

	private Long ids[];

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
	 * @return the answer
	 */
	public String getAnswer()
	{
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	/**
	 * @return the categories
	 */
	public String[] getCategories()
	{
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(String[] categories)
	{
		this.categories = categories;
	}

	/**
	 * @return the ids
	 */
	public Long[] getIds()
	{
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(Long[] ids)
	{
		this.ids = ids;
	}

}
