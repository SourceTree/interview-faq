/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * QuestionExcelView.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 21, 2012			chalam						Created
 * *************************************************************
 */
package org.sourcetree.interview.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.sourcetree.interview.dto.CategoryDTO;
import org.sourcetree.interview.dto.QuestionDTO;
import org.sourcetree.interview.support.CoreUtil;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * @author chalam
 * 
 */
public class QuestionExcelView extends AbstractExcelView
{

	private static final int QUESTIONID_CELLNUM = 0;
	private static final int QUESTION_CELLNUM = 1;
	private static final int ANSWER_CELLNUM = 2;
	private static final int CATEGORIES_CELLNUM = 3;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.view.document.AbstractExcelView#
	 * buildExcelDocument(java.util.Map,
	 * org.apache.poi.hssf.usermodel.HSSFWorkbook,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook myWorkBook, HttpServletRequest arg2,
			HttpServletResponse arg3) throws Exception
	{
		@SuppressWarnings("unchecked")
		List<QuestionDTO> questionDTOs = (List<QuestionDTO>) model
				.get("questions");

		HSSFSheet mySheet = myWorkBook.createSheet();
		HSSFRow headerRow = mySheet.createRow(0);
		// mySheet.protectSheet("");
		HSSFCellStyle readOnlyCellStyle = myWorkBook.createCellStyle();
		readOnlyCellStyle.setLocked(true);
		HSSFCellStyle wrapCellStyle = myWorkBook.createCellStyle();
		wrapCellStyle.setWrapText(true);

		headerRow.createCell(QUESTIONID_CELLNUM).setCellValue("QuestionID");
		headerRow.createCell(QUESTION_CELLNUM).setCellValue("Question");
		headerRow.createCell(ANSWER_CELLNUM).setCellValue("Answer");
		headerRow.createCell(CATEGORIES_CELLNUM).setCellValue(
				"Categories (Category seperated by comma)");

		HSSFRow dataRow = null;

		if (!CoreUtil.isEmpty(questionDTOs))
		{

			for (int i = 0; i < questionDTOs.size(); i++)
			{

				dataRow = mySheet.createRow(i + 1);
				QuestionDTO questionDTO = questionDTOs.get(i);

				dataRow.createCell(QUESTIONID_CELLNUM).setCellValue(
						questionDTO.getId());

				HSSFCell questionDataCell = dataRow
						.createCell(QUESTION_CELLNUM);
				questionDataCell.setCellValue(questionDTO.getQuestion());
				questionDataCell.setCellStyle(wrapCellStyle);

				HSSFCell answerDataCell = dataRow.createCell(ANSWER_CELLNUM);
				answerDataCell.setCellValue(questionDTO.getAnswer());
				answerDataCell.setCellStyle(wrapCellStyle);
				String categoryExcelData = "";
				if (!CoreUtil.isEmpty(questionDTO.getCategoryDTOs()))
				{
					StringBuilder sb = new StringBuilder();
					for (CategoryDTO questionCategoryDTO : questionDTO
							.getCategoryDTOs())
					{
						sb.append(questionCategoryDTO.getCategoryName())
								.append(",");
					}
					categoryExcelData = sb.substring(0, sb.length() - 1)
							.toString();

				}
				HSSFCell categoriesDataCell = dataRow
						.createCell(CATEGORIES_CELLNUM);
				categoriesDataCell.setCellValue(categoryExcelData);
				categoriesDataCell.setCellStyle(wrapCellStyle);

			}
		}

		mySheet.autoSizeColumn(QUESTIONID_CELLNUM);
		mySheet.autoSizeColumn(QUESTION_CELLNUM);
		mySheet.autoSizeColumn(ANSWER_CELLNUM);
		mySheet.autoSizeColumn(CATEGORIES_CELLNUM);
		// mySheet.setDefaultColumnStyle(QUESTIONID_CELLNUM, readonlyCellStyle);
		mySheet.setDefaultColumnStyle(QUESTION_CELLNUM, wrapCellStyle);
		mySheet.setDefaultColumnStyle(ANSWER_CELLNUM, wrapCellStyle);
		mySheet.setDefaultColumnStyle(CATEGORIES_CELLNUM, wrapCellStyle);

	}

}
