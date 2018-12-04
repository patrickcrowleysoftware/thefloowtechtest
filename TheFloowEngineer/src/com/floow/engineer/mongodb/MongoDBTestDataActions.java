package com.floow.engineer.mongodb;

import com.mongodb.client.FindIterable;

public class MongoDBTestDataActions {

	// This class is not strictly required for the Floow Challenge - its an initial class I created to prove a Java connection to 
	// MongoDB data entered manually through the MongoDB Shell
	
	// Mongo database name
	private static String MONGODB_NAME = "patsFirstDB";
	
	// MongoDB Database context
	private MongoDBConnection mdb;
	
	public MongoDBTestDataActions(MongoDBConnection mdb) {
		
		this.mdb = mdb;
		
		// Connect to this MongoDB database
		this.mdb.connectDb(MONGODB_NAME);

	}

	public void performTestReads() {
		
		// Run MongoDB queries
		
		FindIterable docs = mdb.findByKey("myCollection", "status", "A", (value) -> new String(value));
		
		for (Object doc : docs) {
			
            System.out.println(doc.toString());
        }
	}

}
