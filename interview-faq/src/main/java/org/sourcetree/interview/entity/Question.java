/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * Question.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 06, 2012		Chalam Pavuluri				Created
 * *************************************************************
 */
package org.sourcetree.interview.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Question Entity
 * 
 * @author Chalam Pavuluri
 */
@Entity
@Table(name = "question")
@GenericGenerator(strategy = "native", name = "QuestionSeq")
public class Question extends AbstractEntity
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "QuestionesSeq")
	@Column(name = "questionId")
	private Long questionId;

	@Column(name = "question", length = 200)
	private String question;

	private Set<Category> categories = new HashSet<Category>();

	/**
	 * @return the categories
	 */
	@ManyToMany
	@JoinTable(name = "CATEGORY_QUESTION", joinColumns = @JoinColumn(
			name = "questionId"), inverseJoinColumns = @JoinColumn(
			name = "categoryId"))
	public Set<Category> getCategories()
	{
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(Set<Category> categories)
	{
		this.categories = categories;
	}

	/**
	 * @return the questionId
	 */
	public Long getQuestionId()
	{
		return questionId;
	}

	/**
	 * @param questionId
	 *            the questionId to set
	 */
	public void setQuestionId(Long questionId)
	{
		this.questionId = questionId;
	}

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

}
