package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.JSONException;
import org.json.simple.*;
import org.json.simple.parser.*;

// collection of some utility functions used throughout the application
public class utils {
	// converts JSONArray to List
	private static List<Object> toList(JSONArray array) throws JSONException {
	    List<Object> list = new ArrayList<Object>();
	    for(int i = 0; i < array.size(); i++) {
	        Object value = array.get(i);
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        list.add(value);
	    }
	    return list;
	}

	// convert Json Object to Map
	private static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
	    Map<String, Object> retMap = new HashMap<String, Object>();
	    
	    if(json != null) {
	        retMap = toMap(json);
	    }
	    return retMap;
	}
	
	// convert Json Object to Map
	private static Map<String, Object> toMap(JSONObject object) throws JSONException {
	    Map<String, Object> map = new HashMap<String, Object>();

	    Iterator<String> keysItr = object.keySet().iterator();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next();
	        Object value = object.get(key);
	        
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }
	        
	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject)value);
	        }
	        map.put(key, value);
	    }
	    return map;
	}

	// return marks for each difficulty based upon its percentage
	static Integer getDifficultyMarks(Integer totalMarks, Integer difficultyPercentage) {
		return ( totalMarks * difficultyPercentage ) / 100;
	}
	
	// generates Question List for each difficulty from database
	static List<Map<String,Object>> generateDifficultyBasedList(JSONArray questionList) {
		List<Map<String,Object>> generatedList = new ArrayList<Map<String, Object>>();
		 	try {
		 		for (Integer i = 0; i < questionList.size();i++) {
		 			JSONObject jsonObject1 = (JSONObject) JSONValue.parse(questionList.get(i).toString());
		 			generatedList.add(jsonToMap(jsonObject1));
		 		}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		
		return generatedList;
	}
	
}
