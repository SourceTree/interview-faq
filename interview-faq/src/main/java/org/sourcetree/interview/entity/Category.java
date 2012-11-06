/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * Category.java
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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Category Entity
 * 
 * @author Chalam Pavuluri
 */
@Entity
@Table(name = "category")
@GenericGenerator(strategy = "native", name = "CategorySeq")
public class Category extends AbstractEntity
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "CategorySeq")
	@Column(name = "categoryId")
	private Long categoryId;

	@Column(name = "name", length = 80)
	private String categoryName;

	@Column(name = "categoryDescription", length = 150, nullable = true)
	private String categoryDescription;

	private Set<Question> questions = new HashSet<Question>();

	/**
	 * @return the questions
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CATEGORY_QUESTION", joinColumns = @JoinColumn(
			name = "categoryId"), inverseJoinColumns = @JoinColumn(
			name = "questionId"))
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

	/**
	 * @return the categoryId
	 */
	public Long getCategoryId()
	{
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Long categoryId)
	{
		this.categoryId = categoryId;
	}

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

}
