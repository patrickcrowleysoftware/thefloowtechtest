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
	private static String MONGODB_NAME = "theFloowChallengeMongoDatabase";
	
	// MongoDB collection names
	// The words from the sample text
	private static String MONGODB_COLLECTION_NAME_TEXT_WORDS = "flowcolltextwords";
	// Interesting statistics on the words from the sample text
	private static String MONGODB_COLLECTION_NAME_TEXT_STATS = "flowcolltextstats";

	// MongoDB main key names
	// The words from the sample text
	private static String MONGODB_KEY_NAME_TEXT_WORD = "textword";

	// Interesting summary statistics on the words from the sample text
	private static String MONGODB_KEY_NAME_TEXT_STATS	= "textstats";
	
	
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
		
		this.mdb.createCollection(MONGODB_COLLECTION_NAME_TEXT_STATS);
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

		MongoCollection collection = this.mdb.getCollection(MONGODB_COLLECTION_NAME_TEXT_STATS);

		// Max frequency word
		writeTextStatsPart(collection, wordCountMap, CalculateTextStats.getStatsHighestFrequency(wordCountMap));
		
		// Max Vowels word
		writeTextStatsPart(collection, wordCountMap, CalculateTextStats.getStatsMostVowels(wordCountMap));

		// Max Consonants word
		writeTextStatsPart(collection, wordCountMap, CalculateTextStats.getStatsMostConsonants(wordCountMap));

		// Longest word
		writeTextStatsPart(collection, wordCountMap, CalculateTextStats.getStatsLongestWord(wordCountMap));
        
        // Words containing the most of the letter "<insert letter>" - just one - the letter "b" is given as an example 
 		// as it is probably processor intensive to do all 26 letters!
		writeTextStatsPart(collection, wordCountMap, CalculateTextStats.getStatsWordWithMostOccurrancesOfChar(wordCountMap, 'b'));

 	}
	
	private void writeTextStatsPart(MongoCollection collection, HashMap<String, WordStats> wordCountMap, DBObject dbo) {
	  	
		Document doc = new Document();
    	
		doc.put(MONGODB_KEY_NAME_TEXT_STATS, dbo);
 		
    	collection.insertOne(doc);
	}
	
	
}
