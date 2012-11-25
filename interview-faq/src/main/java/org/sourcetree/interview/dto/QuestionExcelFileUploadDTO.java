/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * QuestionExcelFileUploadDTO.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 22, 2012		chalam						Created
 * *************************************************************
 */
package org.sourcetree.interview.dto;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author chalam
 * 
 */
public class QuestionExcelFileUploadDTO extends BaseDTO
{
	private static final long serialVersionUID = 1L;

	@NotNull(message = "typeMismatch.questionExcelFileUploadDTO.file")
	private MultipartFile file;

	/**
	 * @return the file
	 */
	public MultipartFile getFile()
	{
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(MultipartFile file)
	{
		this.file = file;
	}

}
