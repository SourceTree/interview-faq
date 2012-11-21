/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * QuestionListDTO.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 20, 2012		SRINU SOMINENI				Created
 * *************************************************************
 */
package org.sourcetree.interview.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author SRINU SOMINENI
 * 
 */

/**
 * List of questions with listing data.
 * 
 * @author SRINU SOMINENI
 */
@XmlRootElement(name = "questions")
public class QuestionListDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<QuestionDTO> questionDTOs;

	private ListProp listProp;

	/**
	 * @return the questionDTOs
	 */
	public List<QuestionDTO> getQuestionDTOs()
	{
		return questionDTOs;
	}

	/**
	 * @param questionDTOs
	 *            the questionDTOs to set
	 */
	public void setQuestionDTOs(List<QuestionDTO> questionDTOs)
	{
		this.questionDTOs = questionDTOs;
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
	public QuestionListDTO()
	{

	}

	/**
	 * parameterized constructor
	 * 
	 * @param questionDTOs
	 * @param listProp
	 */
	public QuestionListDTO(List<QuestionDTO> questionDTOs, ListProp listProp)
	{
		this.questionDTOs = questionDTOs;
		this.listProp = listProp;
	}
}
