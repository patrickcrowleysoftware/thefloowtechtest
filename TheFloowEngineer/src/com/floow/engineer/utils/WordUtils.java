package com.floow.engineer.utils;

public class WordUtils {

	public WordUtils() {

	}

	/**
	 * @param word - the word whose vowels are to be counted
	 * 
	 * @return - the number of vowels in the word
	 */
	public static int countVowels(String word) {
		
        int vowelCount = 0;
        
        String lcWord = word.toLowerCase();
        
        for (int i = 0; i < lcWord.length(); i++)
        {
            if (lcWord.charAt(i) == 'a' || lcWord.charAt(i) == 'e' || lcWord.charAt(i) == 'i'
                    || lcWord.charAt(i) == 'o' || lcWord.charAt(i) == 'u')
            {
            	vowelCount++;
            }
        }
        return vowelCount;
    }
	
	/**
	 * @param word - the word whose consonants are to be counted
	 * 
	 * @return - the number of consonants in the word
	 */
	public static int countConsonants(String word) {
		
        int consonantCount = 0;
        
        String lcWord = word.toLowerCase();
        
        for (int i = 0; i < lcWord.length(); i++)
        {
            if (lcWord.charAt(i) != 'a' && lcWord.charAt(i) != 'e' && lcWord.charAt(i) != 'i'
                    && lcWord.charAt(i) != 'o' && lcWord.charAt(i) != 'u')
            {
            	consonantCount++;
            }
        }
        return consonantCount;
    }
	
	/**
	 * @param word - the word to be checked
	 * 
	 * @return - true if all letters are upper-case
	 */
	public static boolean isAllUpperCase(String word)
	{
	    for (int i=0; i < word.length(); i++)
	    {
	        if (!Character.isUpperCase(word.charAt(i)))
	        {
	            return false;
	        }
	    }
	    return true;
	}

	/**
	 * @param word - the word to be checked
	 * 
	 * @return - true if all letters are lower-case
	 */
	public static boolean isAllLowerCase(String word)
	{
	    for (int i=0; i < word.length(); i++)
	    {
	        if (!Character.isLowerCase(word.charAt(i)))
	        {
	            return false;
	        }
	    }
	    return true;
	}

	/**
	 * @param word - the line to be processed
	 * 
	 * @return - the processed line, free of some special characters
	 */
	public static String stripSpecialCharactersFromLine(String textLine) {
		
		StringBuffer cleanedLine = new StringBuffer();
		Character ch;
		
		for (int i=0; i < textLine.length(); i++)
	    {
			ch = textLine.charAt(i);
			
        	// TODO: might allow more special characters in this logic
	        if (Character.isAlphabetic(ch) || Character.isDigit(ch) || ch.charValue() == '-' || ch.charValue() == ' ') {
	        
	        	cleanedLine.append(ch);
	        }
	    }
	    
		return cleanedLine.toString();
	}
	
}
