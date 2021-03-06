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

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

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
	@Column(name = "id")
	private Long id;

	@Column(name = "name", length = 80, nullable = false, unique = true)
	@Index(name = "category_name")
	private String categoryName;

	@Column(name = "displayname", length = 80, nullable = false)
	private String categoryDisplayName;

	@Column(name = "categoryDescription", length = 500, nullable = false)
	private String categoryDescription;

	@ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
	private List<Question> questions;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinTable(name = "PARENT_CATEGORY", joinColumns = @JoinColumn(
			name = "category_id"), inverseJoinColumns = @JoinColumn(
			name = "parent_category_id"))
	private Category parentCategory;

	/**
	 * @return the questions
	 */
	public List<Question> getQuestions()
	{
		return questions;
	}

	/**
	 * @param questions
	 *            the questions to set
	 */
	public void setQuestions(List<Question> questions)
	{
		this.questions = questions;
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
	 * @return the parentCategory
	 */
	public Category getParentCategory()
	{
		return parentCategory;
	}

	/**
	 * @param parentCategory
	 *            the parentCategory to set
	 */
	public void setParentCategory(Category parentCategory)
	{
		this.parentCategory = parentCategory;
	}

	/**
	 * @return the categoryDisplayName
	 */
	public String getCategoryDisplayName()
	{
		return categoryDisplayName;
	}

	/**
	 * @param categoryDisplayName
	 *            the categoryDisplayName to set
	 */
	public void setCategoryDisplayName(String categoryDisplayName)
	{
		this.categoryDisplayName = categoryDisplayName;
	}

}
