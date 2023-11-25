package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GenerateQuestionPaper {
	
	private Integer marks;
	private List<Map<String,Object>> questionList;
	
	// Constructor
	GenerateQuestionPaper(Integer marks, List<Map<String,Object>> questionList) {
		this.marks = marks;
		this.questionList = questionList;
	}
	
	// return marks carrying each Question
	private Integer getMarkForEachQuestion() {
		return (Integer.parseInt(questionList.get(0).get(Constants.marks).toString()));
	}
	
	// returns question count for each difficulty
	private Integer getQuestionCount(Integer eachQuestionMark) {
		if(!Validations.isIndividualDifficultyLevelMarksDistributionPossible(marks,eachQuestionMark,
				questionList.size())) {
			return -1;
		}
		
		Integer questionCount = marks / eachQuestionMark;
		return questionCount;
	}
	
	// random function to shuffle the question dataset
	private void randomize(Integer n)
    {
        // Creating a object for Random class
        Random r = new Random();
         
        // Start from the last element and swap one by one. We don't
        // need to run for the first element that's why i > 0
        for (Integer i = n-1; i > 0; i--) {
             
            // Pick a random index from 0 to i
            Integer j = r.nextInt(i+1);
             
            // Swap questionList[i] with the element at random index
            Collections.swap(questionList, i, j);
        }
    }
	
	// generates Question Paper from question set
	public ArrayList<Map<String,Object>> questionPaperGenerator() {
		ArrayList<Map<String,Object>> questionPaper = new ArrayList<Map<String, Object>>();
		
		Integer eachQuestionMark = getMarkForEachQuestion();
		Integer questionCount = getQuestionCount(eachQuestionMark);
		
		if(questionCount == -1) {
			System.out.println("Question Paper cannot be generated due to limited Question set");
			return null;
		}
		
		randomize(questionList.size());
		
		for(Integer i = 0 ; i < questionCount ; i++) {
			questionPaper.add(questionList.get(i));
		}
		
		return questionPaper;
	}
	
}
