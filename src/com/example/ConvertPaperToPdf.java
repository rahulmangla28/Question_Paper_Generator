package com.example;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

//import javax.swing.text.TabStop;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
//import com.itextpdf.layout.property.TabAlignment;

public class ConvertPaperToPdf {
	
	List<Map<String,Object>> questionsList;
	Integer totalMarks;
	
	// Constructor
	ConvertPaperToPdf(List<Map<String,Object>> questionsList, Integer totalMarks) {
		this.questionsList = questionsList;
		this.totalMarks = totalMarks;
	}
	
	public void convertor()
    {	
        try {
        	// Get the user's home directory
            String userHome = System.getProperty("user.home");

            // Create a File object for the download folder
            File downloadFolder = new File(userHome + "/Downloads/test.pdf");

            // Get the absolute path of the download folder
            String downloadFolderPath = downloadFolder.getAbsolutePath();
            
            // set path
        	Document document = new Document();
        	PdfWriter.getInstance(document, new FileOutputStream(downloadFolderPath));

        	document.open();
        	
    //    	List<TabStop> tabstops = new ArrayList<TabStop>();
            // don't forget to make a tab stop that aligns to the right
  //          tabstops.add(new TabStop(325, TabAlignment.RIGHT));

        	Chunk glue = new Chunk(new VerticalPositionMark());
        	
        	// set Title and other information
        	Paragraph title = new Paragraph("Question Paper");
        	title.setAlignment(Element.ALIGN_CENTER);
        	document.add(title);
        	
        	title = new Paragraph("Instructions : \n");
        	title.add("1. Please attempt all the questions.\n");
        	title.add("2. Use of scientific calculator is permitted.");
        	title.setAlignment(Element.ALIGN_LEFT);
        	document.add(title);
        	
        	title = new Paragraph("Total Marks : ");
        	title.add(totalMarks.toString());
        	title.setAlignment(Element.ALIGN_RIGHT);
        	document.add(title);
        	
        	
        	// extracting each question from list and adding it to PDF document
        	for(Integer i = 0 ; i < questionsList.size(); i++) {
        		title = new Paragraph("Ques " + (i+1) + " - ");
            	title.add(questionsList.get(i).get(Constants.ques).toString().trim());
            	title.setAlignment(Element.ALIGN_LEFT);
                
            	title.add(glue);
            	
            	title.add("(" + questionsList.get(i).get(Constants.marks).toString() + ")");
            	//title.setAlignment(Element.ALIGN_RIGHT);
            	document.add(title);
        	}
        	
         // close the document
        	document.close();
        	
            System.out.println(
                "Question Paper has been successfully downloaded at :"
                + downloadFolderPath);
        }
        catch (Exception e) {
            System.out.println(
                "failed to download the Question Paper due to "
                + e);
        }
    }
}
