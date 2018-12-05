package com.floow.engineer.mongodb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import java.util.Map.Entry;

import com.floow.engineer.text.CalculateTextStats;
import com.floow.engineer.text.WordStats;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class MongoDBFloowDataActions {

	// MongoDB database name
	private static String MONGODB_NAME = "theFloowChallengeDatabase";
	
	// MongoDB collection names
	// The words from the sample text
	private static String MONGODB_COLLECTION_NAME_TEXT_WORDS = "colltextwords";
	// Interesting statistics on the words from the sample text
	private static String MONGODB_COLLECTION_NAME_TEXT_STATS1 = "colltextstats1";
	private static String MONGODB_COLLECTION_NAME_TEXT_STATS2 = "colltextstats2";
	private static String MONGODB_COLLECTION_NAME_TEXT_STATS3 = "colltextstats3";
	private static String MONGODB_COLLECTION_NAME_TEXT_STATS4 = "colltextstats4";
	private static String MONGODB_COLLECTION_NAME_TEXT_STATS5 = "colltextstats5";

	// MongoDB main key names
	// The words from the sample text
	private static String MONGODB_KEY_NAME_TEXT_WORD = "textword";

	// Interesting statistics on the words from the sample text
	private static String MONGODB_KEY_NAME_STATS_MAX_FREQUENCY 			= "statsmaxfrequency";
	private static String MONGODB_KEY_NAME_STATS_MAX_VOWELS 			= "statsmaxvowels";
	private static String MONGODB_KEY_NAME_STATS_MAX_CONSONANTS 		= "statsmaxconsonants";
	private static String MONGODB_KEY_NAME_STATS_LONGEST_WORD 			= "statslongestword";
	private static String MONGODB_KEY_NAME_STATS_WORD_MOST_OF_LETTER 	= "statswordmostofletter";
	
	
	// MongoDB Database context
	private MongoDBConnection mdb;
	
	public MongoDBFloowDataActions(MongoDBConnection mdb) {
		
		this.mdb = mdb;
		
		// Connect to this MongoDB database
		this.mdb.connectDb(MONGODB_NAME);

	}

	public void createTextWordsCollection() {
		
		this.mdb.createCollection(MONGODB_COLLECTION_NAME_TEXT_WORDS);
	}
	
	public void createTextStatsCollection() {
		
		this.mdb.createCollection(MONGODB_COLLECTION_NAME_TEXT_STATS1);
		this.mdb.createCollection(MONGODB_COLLECTION_NAME_TEXT_STATS2);
		this.mdb.createCollection(MONGODB_COLLECTION_NAME_TEXT_STATS3);
		this.mdb.createCollection(MONGODB_COLLECTION_NAME_TEXT_STATS4);
		this.mdb.createCollection(MONGODB_COLLECTION_NAME_TEXT_STATS5);
	}
	
	
	public void writeTextWords(HashMap<String, WordStats> wordCountMap) {
		
		MongoCollection collection = this.mdb.getCollection(MONGODB_COLLECTION_NAME_TEXT_WORDS);
		
		// Convert the raw list to a set
	    Set<Entry<String, WordStats>> entrySet = wordCountMap.entrySet();
	    
		// Convert the set to a list that can be processed by MongoDB
	    List<Document> wordList = new ArrayList(entrySet);

        for (Entry<String, WordStats> entry : entrySet)
        {
        	// Write a word entry
        	// System.out.println("Putting word: "+entry.getKey());
    		
        	Document doc = new Document();
        	doc.put(MONGODB_KEY_NAME_TEXT_WORD, ((WordStats) entry.getValue()).toDBObject());
        	
    		collection.insertOne(doc);
        }
        
 	}

	
	public void writeTextStats(HashMap<String, WordStats> wordCountMap) {
		
        // Calculate and write overall word statistics calculation methods

		// Max frequency word
		MongoCollection collection1 = this.mdb.getCollection(MONGODB_COLLECTION_NAME_TEXT_STATS1);
		DBObject dbo1 = CalculateTextStats.getStatsHighestFrequency(wordCountMap);
      	Document doc1 = new Document();
    	doc1.put(MONGODB_KEY_NAME_STATS_MAX_FREQUENCY, dbo1);
 		collection1.insertOne(doc1);

		// Max Vowels word
		MongoCollection collection2 = this.mdb.getCollection(MONGODB_COLLECTION_NAME_TEXT_STATS2);
		DBObject dbo2 = CalculateTextStats.getStatsMostVowels(wordCountMap);
      	Document doc2 = new Document();
    	doc1.put(MONGODB_KEY_NAME_STATS_MAX_VOWELS, dbo2);
 		collection2.insertOne(doc2);

		// Max Consonants word
		MongoCollection collection3 = this.mdb.getCollection(MONGODB_COLLECTION_NAME_TEXT_STATS3);
		DBObject dbo3 = CalculateTextStats.getStatsMostConsonants(wordCountMap);
      	Document doc3 = new Document();
    	doc1.put(MONGODB_KEY_NAME_STATS_MAX_CONSONANTS, dbo3);
 		collection3.insertOne(doc3);

		// Longest word
		MongoCollection collection4 = this.mdb.getCollection(MONGODB_COLLECTION_NAME_TEXT_STATS4);
		DBObject dbo4 = CalculateTextStats.getStatsLongestWord(wordCountMap);
      	Document doc4 = new Document();
    	doc1.put(MONGODB_KEY_NAME_STATS_LONGEST_WORD, dbo4);
 		collection4.insertOne(doc4);
        
        // Words containing the most of the letter "<insert letter>" - just one - the letter "b" is given as an example 
 		// as it is probably processor intensive to do all 26 letters!
		MongoCollection collection5 = this.mdb.getCollection(MONGODB_COLLECTION_NAME_TEXT_STATS5);
 		DBObject dbo5 = CalculateTextStats.getStatsWordWithMostOccurrancesOfChar(wordCountMap, 'b');
      	Document doc5 = new Document();
    	doc1.put(MONGODB_KEY_NAME_STATS_WORD_MOST_OF_LETTER, dbo5);
 		collection5.insertOne(doc5);

 	}
	
}
