package com.floow.engineer.text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.floow.engineer.utils.WordUtils;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class ProcessText {

	public ProcessText() {
	
	}
	
    public void processText(String textFilePath, HashMap<String, WordStats> wordCountMap) throws IOException { 
    	
        BufferedReader reader = null;
         
        try
        {
            //Creating BufferedReader object
             
            reader = new BufferedReader(new FileReader(textFilePath));
             
            //Reading the first line into currentLine
             
            String currentLine = reader.readLine();
             
            while (currentLine != null)
            {    
            	
            	// Strip any unwanted characters from the text line 
                currentLine = WordUtils.stripSpecialCharactersFromLine(currentLine);
            	
                //splitting the currentLine into words
            	String[] words = currentLine.split(" ");
                 
                //Iterating each word
                 
                for (String word : words)
                {
                    //if word is already present in wordCountMap, updating its count
                     
                    if(wordCountMap.containsKey(word))
                    {    
                    	WordStats ws = wordCountMap.get(word);
                    	ws.incrementFrequency();
                    }
                    //otherwise inserting the word as key for the first time
                    else
                    {
                        wordCountMap.put(word, new WordStats(word));
                    }
                }
                 
                //Reading next line into currentLine
                 
                currentLine = reader.readLine();
            }
             
            // Use for debug only
            // CalculateTextStats.dumpWordList(wordCountMap);
            
        } finally {
            
            reader.close();           //Closing the reader
        }

        return;
    }    
    
}
