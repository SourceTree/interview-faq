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

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;

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
	@GeneratedValue(generator = "QuestionSeq")
	@Column(name = "id")
	private Long id;

	@Column(name = "question", length = 200, nullable = false)
	private String question;

	@Column(name = "answer", length = 5000, nullable = false)
	private String answer;

	// @ManyToMany(fetch = FetchType.EAGER)
	// @Cascade(value = CascadeType.SAVE_UPDATE)

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "CATEGORY_QUESTION", joinColumns = @JoinColumn(
			name = "question_id"), inverseJoinColumns = @JoinColumn(
			name = "category_id"))
	private List<Category> categories;

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
	public void setQuestionId(Long id)
	{
		this.id = id;
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
