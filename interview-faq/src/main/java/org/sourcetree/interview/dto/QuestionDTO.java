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

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.sourcetree.interview.support.validation.SecondGroup;

/**
 * @author Chalam Pavuluri
 */
@XmlRootElement(name = "question")
public class QuestionDTO extends BaseDTO
{
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "question.null")
	@Size(min = 3, max = 200, message = "question.length",
			groups = SecondGroup.class)
	private String question;

	@Size(min = 3, max = 5000, message = "answer.length",
			groups = SecondGroup.class)
	@SafeHtml(message = "asnwer.html")
	private String answer;

	private List<CategoryDTO> categoryDTOs;

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
}
