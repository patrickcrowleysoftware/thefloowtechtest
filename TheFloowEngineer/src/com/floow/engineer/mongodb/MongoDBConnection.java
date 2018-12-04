package com.floow.engineer.mongodb;


import java.net.UnknownHostException;
import java.util.List;
import java.util.function.Function;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class MongoDBConnection {
    
	private static MongoDBConnection ctx;
    
	// Mongo Client
    private  MongoClient client;
    
    // Mongo Database
    private  MongoDatabase mdb;
    
    private MongoDBConnection(String hostName, String port){
    
    	try{
        
    		init(hostName, port);
        
    	}catch(Exception ex){
            ex.printStackTrace();
            
        }
    }
    
    private void init(String hostName, String port) throws UnknownHostException{
    
    	this.client = new MongoClient(hostName, Integer.parseInt(port));
    
    }
    
    public void close() {
    	
    	this.client.close();
    }
    
    public static MongoDBConnection get(String hostName, String port){
    
    	ctx = new MongoDBConnection(hostName, port);
    	
    	return ctx;
    }
    
    public MongoDBConnection connectDb(String dbname){
        
    	if(mdb !=null){
            throw new RuntimeException("Already conected to " + mdb.getName() + "can't connect " + dbname);
        }
    	
        this.mdb = client.getDatabase(dbname);
        
        System.out.println("DB Details :: " + mdb.getName());
        
        return ctx;
    }
    
    public <T,X> FindIterable findByKey(String collectionName,String key,T value, Function<T,X> convertDataType) {
            
    	MongoCollection collection = mdb.getCollection(collectionName);
        
    	BasicDBObject searchQuery = new BasicDBObject();
        
    	searchQuery.put(key, convertDataType.apply(value));
        
    	//System.out.println("search Query ::" + searchQuery);
        
    	FindIterable cursor = collection.find(searchQuery);
    
    	
        return cursor;
    }
    

    // Create a MongoDB database collection for the given collection name
    public void createCollection(String collectionName) {
    	
    	try {
        	mdb.createCollection(collectionName);
    		
    	} catch (Exception e) {
    		// Allow for collection already created	
    	}
    }
    
    // Return the MongoDB database collection for the given collection name
    public MongoCollection<Document> getCollection(String collectionName) {
    	
    	return mdb.getCollection(collectionName);
    }
    
    // Show the list of MOngoDB database names for this connection
    public void showDatabaseNames() {
    	
    	List<String> dbNames= client.getDatabaseNames();
    	
		System.out.println("MongoDB Names:");
		
    	for (String dbName : dbNames) {
    		
			System.out.println("Name: "+dbName);
		}

		System.out.println(" ");

    }
    
}
