
DATABASE INFO
-------------

MONGODB VERSION INSTALLED ON WINDOWS 10
---------------------------------------
Using DB Version V4.0.4

Sample start-up console output:

C:\WINDOWS\system32>cd C:\Program Files\MongoDB\Server\4.0\bin

C:\Program Files\MongoDB\Server\4.0\bin>mongod.exe --dbpath="c:\data\db"
2018-12-02T12:51:37.198+0000 I CONTROL  [main] Automatically disabling TLS 1.0, to force-enable TLS 1.0 specify --sslDisabledProtocols 'none'
2018-12-02T12:51:37.521+0000 I CONTROL  [initandlisten] MongoDB starting : pid=19296 port=27017 dbpath=c:\data\db 64-bit host=PATSPC
2018-12-02T12:51:37.521+0000 I CONTROL  [initandlisten] targetMinOS: Windows 7/Windows Server 2008 R2
2018-12-02T12:51:37.522+0000 I CONTROL  [initandlisten] db version v4.0.4
2018-12-02T12:51:37.522+0000 I CONTROL  [initandlisten] git version: f288a3bdf201007f3693c58e140056adf8b04839
2018-12-02T12:51:37.522+0000 I CONTROL  [initandlisten] allocator: tcmalloc
2018-12-02T12:51:37.522+0000 I CONTROL  [initandlisten] modules: none
2018-12-02T12:51:37.522+0000 I CONTROL  [initandlisten] build environment:
2018-12-02T12:51:37.522+0000 I CONTROL  [initandlisten]     distmod: 2008plus-ssl
2018-12-02T12:51:37.523+0000 I CONTROL  [initandlisten]     distarch: x86_64
2018-12-02T12:51:37.523+0000 I CONTROL  [initandlisten]     target_arch: x86_64
2018-12-02T12:51:37.523+0000 I CONTROL  [initandlisten] options: { storage: { dbPath: "c:\data\db" } }
2018-12-02T12:51:37.529+0000 I STORAGE  [initandlisten] Detected data files in c:\data\db created by the 'wiredTiger' storage engine, so setting the active storage engine to 'wiredTiger'.
2018-12-02T12:51:37.529+0000 I STORAGE  [initandlisten] wiredtiger_open config: create,cache_size=7606M,session_max=20000,eviction=(threads_min=4,threads_max=4),config_base=false,statistics=(fast),log=(enabled=true,archive=true,path=journal,compressor=snappy),file_manager=(close_idle_time=100000),statistics_log=(wait=0),verbose=(recovery_progress),
2018-12-02T12:51:37.702+0000 I STORAGE  [initandlisten] WiredTiger message [1543755097:702427][19296:140733291838544], txn-recover: Main recovery loop: starting at 4/5760 to 5/256
2018-12-02T12:51:37.808+0000 I STORAGE  [initandlisten] WiredTiger message [1543755097:808191][19296:140733291838544], txn-recover: Recovering log 4 through 5
2018-12-02T12:51:37.878+0000 I STORAGE  [initandlisten] WiredTiger message [1543755097:878203][19296:140733291838544], txn-recover: Recovering log 5 through 5
2018-12-02T12:51:37.934+0000 I STORAGE  [initandlisten] WiredTiger message [1543755097:933089][19296:140733291838544], txn-recover: Set global recovery timestamp: 0
2018-12-02T12:51:37.957+0000 I RECOVERY [initandlisten] WiredTiger recoveryTimestamp. Ts: Timestamp(0, 0)
2018-12-02T12:51:38.011+0000 I CONTROL  [initandlisten]
2018-12-02T12:51:38.011+0000 I CONTROL  [initandlisten] ** WARNING: Access control is not enabled for the database.
2018-12-02T12:51:38.011+0000 I CONTROL  [initandlisten] **          Read and write access to data and configuration is unrestricted.
2018-12-02T12:51:38.012+0000 I CONTROL  [initandlisten]
2018-12-02T12:51:38.013+0000 I CONTROL  [initandlisten] ** WARNING: This server is bound to localhost.
2018-12-02T12:51:38.014+0000 I CONTROL  [initandlisten] **          Remote systems will be unable to connect to this server.
2018-12-02T12:51:38.014+0000 I CONTROL  [initandlisten] **          Start the server with --bind_ip <address> to specify which IP
2018-12-02T12:51:38.015+0000 I CONTROL  [initandlisten] **          addresses it should serve responses from, or with --bind_ip_all to
2018-12-02T12:51:38.015+0000 I CONTROL  [initandlisten] **          bind to all interfaces. If this behavior is desired, start the
2018-12-02T12:51:38.016+0000 I CONTROL  [initandlisten] **          server with --bind_ip 127.0.0.1 to disable this warning.
2018-12-02T12:51:38.016+0000 I CONTROL  [initandlisten]
2018-12-02T12:51:38.294+0000 I FTDC     [initandlisten] Initializing full-time diagnostic data capture with directory 'c:/data/db/diagnostic.data'
2018-12-02T12:51:38.297+0000 I NETWORK  [initandlisten] waiting for connections on port 27017


See below for info on database name and collections.

DEVELOPMENT ENVIRONMENT
=======================

Eclipse was used as the IDE using Java 8

Create a new Java project called "TheFloowEngineer" in Eclipse

Import the following external jar libraries in Project -> Properties -> Java Build Path -> Add External Jars ..:

1. Argument parsing
-------------------
commons-cli-1.3.1.jar
Can be downloaded from:
https://mvnrepository.com/artifact/commons-cli/commons-cli/1.3.1

2. MongoDB interface
--------------------
bson-3.9.1.jar
Can be downloaded from:
https://mvnrepository.com/artifact/org.mongodb/bson/3.9.1

mongodb-driver-core-3.9.1.jar
Can be downloaded from:
https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-core/3.9.1

mongodb-driver-sync-3.9.1.jar
Can be downloaded from:
https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-sync/3.9.1

mongodb-driver-legacy-3.9.1.jar
Can be downloaded from:
https://oss.sonatype.org/content/repositories/releases/org/mongodb/mongodb-driver-legacy/3.9.1/


3. Import  the source packages and classes layout as listed in this repository under "src" directory.

DATABASE NAMING
===============
MongoDB Database Name amd Collections Names are as defined in the header of the class MongoDBFloowDataActions.java.
There are 2 collections:
1) Collection of unique words in the text with stats per word
2) Collections of summary statistics for all the words in the text


RUNNING THE PROGRAM
===================

Program Arguments format:
–source [source text file path] –mongo [hostname]:[port]

Example:
–source c:\examine\AliceInWonderlandText.txt –mongo localhost:27017

To run within Eclipse:
Right-click on the Java project "TheFloowEngineer" and select Run As -> Java Application
When creating a Run COnfiguration, EngineerMain.java class is the main entry point and the Arguments to set up should be as per the format above.

Sample console output:
---------------------

Floow engineer program started
Input source text file: c:\examine\AliceInWonderlandText.txt
Input MongoDB Host: localhost
Input MongoDB Port: 27017
Dec 05, 2018 7:23:36 AM com.mongodb.diagnostics.logging.JULLogger log
INFO: Cluster created with settings {hosts=[localhost:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms', maxWaitQueueSize=500}
DB Details :: theFloowChallengeDatabase
Dec 05, 2018 7:23:37 AM com.mongodb.diagnostics.logging.JULLogger log
INFO: Cluster description not yet available. Waiting for 30000 ms before timing out
Dec 05, 2018 7:23:37 AM com.mongodb.diagnostics.logging.JULLogger log
INFO: Opened connection [connectionId{localValue:1, serverValue:88}] to localhost:27017
Dec 05, 2018 7:23:37 AM com.mongodb.diagnostics.logging.JULLogger log
INFO: Monitor thread successfully connected to server with description ServerDescription{address=localhost:27017, type=STANDALONE, state=CONNECTED, ok=true, version=ServerVersion{versionList=[4, 0, 4]}, minWireVersion=0, maxWireVersion=7, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=2518067}
Dec 05, 2018 7:23:37 AM com.mongodb.diagnostics.logging.JULLogger log
INFO: Opened connection [connectionId{localValue:2, serverValue:89}] to localhost:27017
The highest frequency word in the text is : "the" occurring 62 times
The word in the text with most vowels is : "waistcoat-pocket" with 6 vowels
The word in the text with most consonants is : "waistcoat-pocket" with 10 consonants
The longest word in the text is : "waistcoat-pocket" with a length of 16 characters
The word in the text with most occurrences of "b" is : "rabbit" with 2 occurrances
Dec 05, 2018 7:23:37 AM com.mongodb.diagnostics.logging.JULLogger log
INFO: Closed connection [connectionId{localValue:2, serverValue:89}] to localhost:27017 because the pool has been closed.

Floow engineer program finished


GENERATING AN EXTERNAL JAR FILE
===============================
I tested the program on a much smaller file "AliceInWonderlandText.txt" as I struggled to download the large Wiki one mentioned in the challenge.
This file is also included in GIT.


MONGO SHELL COMMANDS TO VALIDATE THE DATA
=========================================

> cls

> use theFloowChallengeDatabase

> show collections

> db.colltextwords.find()

> db.colltextstats1.find()

> db.colltextstats2.find()

> db.colltextstats3.find()

> db.colltextstats4.find()

> db.colltextstats5.find()


GENERATING AN EXTERNAL JAR FILE
===============================

I did also export a .jar file also, however I could not get it to run on my Windows 10 laptop (administrator) either at a DOS command prompt or in CygWIn.
I suspect a permission issue.

Typing the following in the same directory as the .jar file:
java –Xmx8192m -jar TheFloowEngineer.jar –source c:\examine\AliceInWonderlandText.txt –mongo localhost:27017

gives the error below:

C:\Pat\MongoDB\TheFloow>dir
 Volume in drive C is OS
 Volume Serial Number is 02E2-FEDA

 Directory of C:\Pat\MongoDB\TheFloow

04/12/2018  10:19    <DIR>          .
04/12/2018  10:19    <DIR>          ..
04/12/2018  10:22                86 jar.sh
04/12/2018  08:49                 0 pat.txt
04/12/2018  08:28             1,455 TheFloowAnt.xml
04/12/2018  10:11         2,151,882 TheFlowEngineer.jar
               4 File(s)      2,153,423 bytes
               2 Dir(s)  337,771,286,528 bytes free

C:\Pat\MongoDB\TheFloow>java -Xmx8192m -jar TheFloowEngineer.jar -source c:\examine\AliceInWonderlandText.txt -mongo localhost:27017
Error: Unable to access jarfile TheFloowEngineer.jar

C:\Pat\MongoDB\TheFloow>



