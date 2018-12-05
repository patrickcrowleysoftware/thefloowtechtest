package com.floow.engineer.text;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class CalculateTextStats {

	public CalculateTextStats() {
		
	}

   public static DBObject getStatsHighestFrequency(HashMap<String, WordStats> wordCountMap) {
    	
        //Getting the most repeated word and its occurrence
        
        String word = null;
         
        int maxNumber = 0;
         
        Set<Entry<String, WordStats>> entrySet = wordCountMap.entrySet();
         
        for (Entry<String, WordStats> entry : entrySet)
        {
        	int frequency = ((WordStats) entry.getValue()).getFrequency();
            if( frequency > maxNumber)
            {
            	maxNumber = frequency;
            	word = entry.getKey();
            }
        }
         
        System.out.println("The highest frequency word in the text is : \"" + word + "\" occurring " + String.valueOf(maxNumber) + " times");
        
        BasicDBObjectBuilder dbStatsBuilder = BasicDBObjectBuilder.start("word", word)
        					.append("statistic", "maxfrequencywordCount "+String.valueOf(maxNumber));
        
        return dbStatsBuilder.get();
    }
      
   public static DBObject getStatsLongestWord(HashMap<String, WordStats> wordCountMap) {
   	
       //Getting the longest word in the text
       
       String word = null;
        
       int maxNumber = 0;
        
       Set<Entry<String, WordStats>> entrySet = wordCountMap.entrySet();
        
       for (Entry<String, WordStats> entry : entrySet)
       {
       	int length = ((WordStats) entry.getValue()).getLength();
           if( length > maxNumber)
           {
           	maxNumber = length;
           	word = entry.getKey();
           }
       }
        
       System.out.println("The longest word in the text is : \"" + word + "\" with a length of " + String.valueOf(maxNumber) + " characters");
       
       BasicDBObjectBuilder dbStatsBuilder = BasicDBObjectBuilder.start("word", word)
				.append("statistic", "maxcharsword "+String.valueOf(maxNumber));

       return dbStatsBuilder.get();
   }
     
   public static DBObject getStatsMostVowels(HashMap<String, WordStats> wordCountMap) {
   	
       //Getting the word with the most vowels in the text
       
       String word = null;
        
       int maxNumber = 0;
        
       Set<Entry<String, WordStats>> entrySet = wordCountMap.entrySet();
        
       for (Entry<String, WordStats> entry : entrySet)
       {
       	int vowels = ((WordStats) entry.getValue()).getVowels();
           if( vowels > maxNumber)
           {
           	maxNumber = vowels;
           	word = entry.getKey();
           }
       }
        
       System.out.println("The word in the text with most vowels is : \"" + word + "\" with " + String.valueOf(maxNumber) + " vowels");
       
       BasicDBObjectBuilder dbStatsBuilder = BasicDBObjectBuilder.start("word", word)
				.append("statistic", "maxnumberofvowels "+String.valueOf(maxNumber));

       return dbStatsBuilder.get();
   }
     
   public static DBObject getStatsMostConsonants(HashMap<String, WordStats> wordCountMap) {
	   	
       //Getting the word with the most consonants in the text
       
       String word = null;
        
       int maxNumber = 0;
        
       Set<Entry<String, WordStats>> entrySet = wordCountMap.entrySet();
        
       for (Entry<String, WordStats> entry : entrySet)
       {
       	int consonants = ((WordStats) entry.getValue()).getConsonants();
           if( consonants > maxNumber)
           {
           	maxNumber = consonants;
           	word = entry.getKey();
           }
       }
        
       System.out.println("The word in the text with most consonants is : \"" + word + "\" with " + String.valueOf(maxNumber) + " consonants");
       
       BasicDBObjectBuilder dbStatsBuilder = BasicDBObjectBuilder.start("word", word)
				.append("statistic", "maxnumberofconsonants "+String.valueOf(maxNumber));

       return dbStatsBuilder.get();
   }
     
   public static DBObject getStatsWordWithMostOccurrancesOfChar(HashMap<String, WordStats> wordCountMap, char ch) {
	   	
       //Getting the word in the text with the most occurrances of character "ch"
       
       String word = null;
        
       int maxNumber = 0;
        
       Set<Entry<String, WordStats>> entrySet = wordCountMap.entrySet();
        
       for (Entry<String, WordStats> entry : entrySet)
       {
    	   
       	   int occurrances = countMatches(entry.getKey(), ch);
           
       	   if( occurrances > maxNumber)
           {
           	maxNumber = occurrances;
           	word = entry.getKey();
           }
       }
        
       System.out.println("The word in the text with most occurrences of \"" + String.valueOf(ch) + "\" is : \"" + word + "\" with " + String.valueOf(maxNumber) + " occurrances");
       
       BasicDBObjectBuilder dbStatsBuilder = BasicDBObjectBuilder.start("word", word)
				.append("statistic", "mostoccurranceofletter-"+String.valueOf(ch)+"-occurrances "+String.valueOf(maxNumber));

       return dbStatsBuilder.get();
   }
     
   private static int countMatches(String s, char ch) {
	   int count=0;
	   
	   for (int i = 0; i < s.length(); i++) {
	       if (s.charAt(i) == ch) {
	           count++;
	       }
	   }
	   
	   return count;
   }
   
   
  public static void dumpWordList(HashMap<String, WordStats> wordCountMap) {
	   
	System.out.println("Dump of unsorted word counts:");
    
    Set<Entry<String, WordStats>> entrySet = wordCountMap.entrySet();

    for (Entry<String, WordStats> entry : entrySet) {
    	
    	System.out.println("Word: "+entry.getKey() + " Stats: " + ((WordStats) entry.getValue()).toString());
    }
	   	   
   }

}
