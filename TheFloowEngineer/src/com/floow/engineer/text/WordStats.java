package com.floow.engineer.text;

import com.floow.engineer.utils.WordUtils;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class WordStats {

	private String word;
	
	private int frequency = 1;

	private int length;
	
	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	private int consonants;
	private int vowels;
	
	private char beginsWith;

	private boolean isAllUpperCase;
	private boolean isAllLowerCase;

	public WordStats() {
		
	}

	public WordStats(String word) {
		
		this.word = word;
		
		// Calculate additional word statistics
		
		if (word != null && !word.isEmpty()) {
			
			this.consonants = WordUtils.countConsonants(word);
			this.vowels = WordUtils.countVowels(word);
			
			this.length = word.length();
			
			this.beginsWith = word.toUpperCase().charAt(0);
			
			this.isAllUpperCase = WordUtils.isAllUpperCase(word);
			this.isAllLowerCase = WordUtils.isAllLowerCase(word);
					
		}
	}
	
	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @return the frequency
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * @param word the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * @return the consonants
	 */
	public int getConsonants() {
		return consonants;
	}

	/**
	 * @param consonants the consonants to set
	 */
	public void setConsonants(int consonants) {
		this.consonants = consonants;
	}

	/**
	 * @return the vowels
	 */
	public int getVowels() {
		return vowels;
	}

	/**
	 * @param vowels the vowels to set
	 */
	public void setVowels(int vowels) {
		this.vowels = vowels;
	}

	/**
	 * @return the isAllUpperCase
	 */
	public boolean isAllUpperCase() {
		return isAllUpperCase;
	}

	/**
	 * @param isAllUpperCase the isAllUpperCase to set
	 */
	public void setAllUpperCase(boolean isAllUpperCase) {
		this.isAllUpperCase = isAllUpperCase;
	}
	
	
	/**
	 * @return the isAllLowerCase
	 */
	public boolean isAllLowerCase() {
		return isAllLowerCase;
	}

	/**
	 * @param isAllLowerCase the isAllLowerCase to set
	 */
	public void setAllLowerCase(boolean isAllLowerCase) {
		this.isAllLowerCase = isAllLowerCase;
	}
	
	/**
	 * @return the beginsWith
	 */
	public char getBeginsWith() {
		return beginsWith;
	}

	/**
	 * @param beginsWith the beginsWith to set
	 */
	public void setBeginsWith(char beginsWith) {
		this.beginsWith = beginsWith;
	}

	/**
	 * Increment the word frequency by 1
	 */
	public void incrementFrequency() {
		
		++this.frequency;
	}

	public DBObject toDBObject() {
		
		String strFrequency = String.valueOf(getFrequency());
		String strLength = String.valueOf(getLength());
		String strVowels = String.valueOf(getVowels());
		String strConsonants = String.valueOf(getConsonants());
		
        BasicDBObjectBuilder builder = BasicDBObjectBuilder
                .start("word", getWord())
                .append("frequency", strFrequency)
                .append("length", strLength)
                .append("vowels", strVowels)
                .append("consonants", strConsonants);
        
        return builder.get();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WordStats [word=" + word + ", frequency=" + frequency + ", consonants=" + consonants + ", vowels="
				+ vowels + ", length=" +length + ", beginsWith=" + beginsWith + ", isAllUpperCase=" + isAllUpperCase + ", isAllLowerCase="
				+ isAllLowerCase + "]";
	}
}
