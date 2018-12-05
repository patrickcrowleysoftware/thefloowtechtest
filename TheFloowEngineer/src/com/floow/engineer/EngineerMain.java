package com.floow.engineer;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;

// Parsing code
import org.apache.commons.cli.*;

// Mongo code
import com.mongodb.client.FindIterable;
import com.floow.engineer.mongodb.MongoDBTestDataActions;
import com.floow.engineer.mongodb.MongoDBConnection;
import com.floow.engineer.mongodb.MongoDBFloowDataActions;
// Text Processing code
import com.floow.engineer.text.ProcessText;
import com.floow.engineer.text.WordStats;

public class EngineerMain {
	
	// INPUT DATA
	// Source Text file path
	private static String sourceTextFilePath;
	
	// MongoDB Connection data
	private static String mongoDBHost;
	private static String mongoDBPort;
	
	// MongoDB Connection Class
	private static MongoDBConnection mdb;
	
	// MongoDB DATA LOGIC CLASSES
	// MongoDB test data actions - TEST ONLY of data entered from the Mongo Shell
	// private static MongoDBTestDataActions mdbTestActions;
	private static MongoDBFloowDataActions mdbFloowActions;
	
	// Text data 
	private static HashMap<String, WordStats> wordCountMap = null;
	
	public EngineerMain() {
	}

	public static void main(String[] args) {

		System.out.println("\nFloow engineer program started");

		try {
			
			// Parse the input arguments - only continue if arguments are valid
			parseOptions(args);

			// Create the MongoDB connection - only continue if connection can be made
			// Usually use "localhost:27017"
			mongoDBConnection(mongoDBHost, mongoDBPort);

			// Extract word statistics from the text file into the list - only continue if the file can be found
			processTextAndCalcStats(sourceTextFilePath);
			
			// Perform MongoDB Floow data actions
			mongoDBPerformFloowDataActions();

			// Perform MongoDB test data actions - TEST ONLY of data entered from the Mongo Shell
			// mongoDBPerformTestDataActions();
			
		} catch (ParseException | IOException  e) {
			
            e.printStackTrace();
			
			System.out.println("Prematurely exiting the program");
            System.exit(1);
		}
		
		// Close MongoDB client
		mdb.close();
		
		System.out.println("\nFloow engineer program finished");
	}

	
	private static void mongoDBConnection(String hostName, String port)  throws UnknownHostException {
		
		// Create a MongoDB database connection
		mdb = MongoDBConnection.get(hostName, port);

		// Show a list of database names for this connection
		// mdb.showDatabaseNames();
		
	}

	private static void processTextAndCalcStats(String sourceTextFile) throws IOException {
		
		ProcessText pt = new ProcessText();

        // Creating wordCountMap which holds words as keys and their occurrences and other statistics as values
	    wordCountMap = new HashMap<String, WordStats>();
	       			
		// Populate the hash map
		pt.processText(sourceTextFile, wordCountMap);
	}
	

	private static void mongoDBPerformFloowDataActions() {

		// Perform MongoDB database updates
		
		mdbFloowActions = new MongoDBFloowDataActions(mdb);
		
		// Create Words collection
		mdbFloowActions.createTextWordsCollection();
		// Write the words data
		mdbFloowActions.writeTextWords(wordCountMap);
		
		// Create interesting statistics collection
		mdbFloowActions.createTextStatsCollection();
		// Write the interesting statistics data
		mdbFloowActions.writeTextStats(wordCountMap);

	}

/*	
	private static void mongoDBPerformTestDataActions() {

		// Perform MongoDB database updates
		
		mdbTestActions = new MongoDBTestDataActions(mdb);
		
		mdbTestActions.performTestReads();

	}
*/

	
	private static void parseOptions(String argsStr[]) throws ParseException {
		
	    Options options = new Options();

        Option source = new Option("source", "source", true, "source text file path");
        source.setRequired(true);
        options.addOption(source);

        Option mongo = new Option("mongo", "mongo", true, "[hostname]:[port]");
        mongo.setRequired(true);
        options.addOption(mongo);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd=null;

        try {
            cmd = parser.parse(options, argsStr);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("TheFlowEngineer", options);

            throw e;
        }

        // Valid input data, now store it
        sourceTextFilePath = cmd.getOptionValue("source");
        
        String mongoConnection = cmd.getOptionValue("mongo");
        String mongoConnectionData[]= mongoConnection.split(":");
        mongoDBHost = mongoConnectionData[0];
        mongoDBPort = mongoConnectionData[1];
        
        System.out.println("Input source text file: " + sourceTextFilePath);
        System.out.println("Input MongoDB Host: " + mongoDBHost);
        System.out.println("Input MongoDB Port: " + mongoDBPort);

        if (sourceTextFilePath != null && !sourceTextFilePath.isEmpty() &&
        	mongoDBHost != null && !mongoDBHost.isEmpty() &&
        	mongoDBPort != null && !mongoDBPort.isEmpty()
        		
        		) {
        	
        } else {
            
            throw new ParseException("One or more input arguments is missing");
        }
        
	}

}
