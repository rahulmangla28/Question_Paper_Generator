package com.example;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.*;
import org.json.simple.parser.*;

public class Question_Selector {
		
	public static void main(String[] args) {
		
		List<Map<String,Object>> easyQuestionSet = new ArrayList<>();
		List<Map<String,Object>> mediumQuestionSet = new ArrayList<>();
		List<Map<String,Object>> hardQuestionSet = new ArrayList<>();
		 
		JSONParser parser = new JSONParser();
	      
	      // Input from user
	      Scanner sc = new Scanner(System.in);
	      System.out.print("Enter Total marks for the paper: ");
	      Integer totalMarks = sc.nextInt();
	      System.out.print("Enter easy section percentage for the paper: ");
	      Integer easyPercentage = sc.nextInt();
	      System.out.print("Enter medium section percentage for the paper: ");
	      Integer mediumPercentage = sc.nextInt();
	      System.out.print("Enter hard section percentage for the paper: ");
	      Integer hardPercentage = sc.nextInt();
	      System.out.print("Enter json file path of the question dataset: ");
	      String jsonFilePath = sc.next();
	      
	      try {
	    	 // File Reader contains the path where Json stored 
	         Object obj = parser.parse(new FileReader(jsonFilePath));
	         JSONObject jsonObject = (JSONObject)obj;
	        
	         easyQuestionSet = utils.generateDifficultyBasedList((JSONArray)jsonObject.get(Constants.easy));
	         mediumQuestionSet = utils.generateDifficultyBasedList((JSONArray)jsonObject.get(Constants.medium));
	         hardQuestionSet = utils.generateDifficultyBasedList((JSONArray)jsonObject.get(Constants.hard));
	         
	         
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      // validating marks distribution
	      if(!Validations.isMarksDistributionPossible(totalMarks, easyPercentage, mediumPercentage, hardPercentage)) {
	    	  return ;
	      }
	      
	      // Final Question Paper
	      ArrayList<Map<String,Object>> questionPaper = new ArrayList<Map<String, Object>>();
	      
	      // question paper containing easy section 
	      Integer requiredEasyMarks = utils.getDifficultyMarks(totalMarks, easyPercentage);
	      ArrayList<Map<String,Object>> easyQuestionPaper = new ArrayList<Map<String, Object>>();
	      
	      GenerateQuestionPaper generateEasyQuestionPaper = new GenerateQuestionPaper(requiredEasyMarks,easyQuestionSet);
	      easyQuestionPaper = generateEasyQuestionPaper.questionPaperGenerator();
	      
	      // check if paper is empty
	      if(easyQuestionPaper == null) return;
	      
	      // add easy section to final question paper
	      questionPaper.addAll(easyQuestionPaper);
	      
	      // question paper containing medium section
	      Integer requiredMediumMarks = utils.getDifficultyMarks(totalMarks, mediumPercentage);
	      ArrayList<Map<String,Object>> mediumQuestionPaper = new ArrayList<Map<String, Object>>();
	      
	      GenerateQuestionPaper generateMediumQuestionPaper = new GenerateQuestionPaper(requiredMediumMarks,mediumQuestionSet);
	      mediumQuestionPaper = generateMediumQuestionPaper.questionPaperGenerator();
	      
	      // check if paper is empty
	      if(mediumQuestionPaper == null) return;
	      
	      // add medium section to final question paper
	      questionPaper.addAll(mediumQuestionPaper);
	      
	      
	      // question paper containing hard section
	      Integer requiredHardMarks = utils.getDifficultyMarks(totalMarks, hardPercentage);
	      ArrayList<Map<String,Object>> hardQuestionPaper = new ArrayList<Map<String, Object>>();
	      
	      GenerateQuestionPaper generateHardQuestionPaper = new GenerateQuestionPaper(requiredHardMarks,hardQuestionSet);      
	      hardQuestionPaper = generateHardQuestionPaper.questionPaperGenerator();
	      
	      // check if paper is empty
	      if(hardQuestionPaper == null) return;
	      
	      // add hard section to final question paper
	      questionPaper.addAll(hardQuestionPaper);
	      
	      // function call to convert paper to PDF
	      ConvertPaperToPdf convertPaperToPdf = new ConvertPaperToPdf(questionPaper,totalMarks);
	      convertPaperToPdf.convertor();
	}

}
