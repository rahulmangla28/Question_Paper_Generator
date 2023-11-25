package com.example;

public class Validations {
	
	// validates whether given difficulty distribution can be aligned with total marks 
	static boolean isMarksDistributionPossible(Integer totalMarks, Integer easyPercentage, 
			Integer mediumPercentage, Integer hardPercentage) {
		
		Integer easyMarks = (easyPercentage * totalMarks ) / 100 ;
	    Integer mediumMarks = (mediumPercentage * totalMarks ) / 100 ;
	    Integer hardMarks = (hardPercentage * totalMarks ) / 100 ;
	      
	    if(easyMarks + mediumMarks + hardMarks != totalMarks) {
	  	  System.out.println("Marks distribution not possible with the provided difficulty percentage");	    	 
	   	  return false; // Change it to throw statement
        }
	    
	    return true;
	}
	
	// validates whether given difficulty weightage is possible or not
	// also validates whether our sample size meets the question count requirements or not
 	static boolean isIndividualDifficultyLevelMarksDistributionPossible(Integer difficultyTotalMarks, 
 			Integer eachQuestionMark, Integer totalAvailablequestions) {
 		if(difficultyTotalMarks % eachQuestionMark != 0) return false;
 		
 		Integer questionCount = difficultyTotalMarks / eachQuestionMark;
 		if(questionCount > totalAvailablequestions) return false;
 		
 		return true;
 	}
	
}
