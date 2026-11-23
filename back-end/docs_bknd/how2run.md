

**************************************************
## 🚀 2. How to Run the Backend (Spring Boot)
Open Terminal 1 (in VS Code or PowerShell):
powershell

### 1. Navigate to the backend directory
cd f:\INTERN_PROJ\TripNestAgFtd_ShreyasBkd\TripNestProjVer2_M2\code_Backend
cd code_Backend
mvn clean compile

### 2. Start the Spring Boot server using the Maven wrapper
mvn spring-boot:run

or
.\mvnw.cmd spring-boot:run

Backend Port: http://localhost:8080
What to look for in the console:
You will see the Spring Boot banner, followed by:
text
[DataInitializer] Seeding complete with destinations & attractions!
Started BackendApplication in X.XXX seconds (process running for X.XXX)


--- 

## 💻 3. How to Run the Frontend (React + Vite)
Open Terminal 2 (side-by-side with Terminal 1):

### 1. Navigate to the frontend directory
cd f:\INTERN_PROJ\TripNestAgFtd_ShreyasBkd\TripNestProjVer2_M2\code_FrontEnd

### 2. (First time only) Ensure packages are installed
npm install

### 3. Start the local development server
npm run dev
Frontend URL: http://localhost:5173
Open your browser to http://localhost:5173 to interact with TripNest!



----

### Step Postgresql : Open psql and create the new database
sql
>> psql -U postgres
Type pw

Once inside:
sql
-- Create the new database for Milestone 2
Recreate using standard lowercase (Recommended practice)
>> CREATE DATABASE "Ur_db_name";    
-- write ur db name
ex- 
>> CREATE DATABASE tripnestdb2ag;


-- Verify it was created
>> \list
or 
>> \l

-- Connect to the new database
>> \c tripnestdb2ag


-------------------

## Github cmds Imp

### link git to github a/c ... actual ... in main github
git remote add origin https://github.com/alpha1zln/TripNestProjAgSy.git   
... chk 1nc

*check if it is linked
git remote -v


### change br name master to main 
git branch -M main

ex-
shreyasHm@shreyas1apzn MINGW64 /f/INTERN_PROJ/TripNestAgFtd_ShreyasBkd/TripNestProjVer2_M2 (master)
```
$ git branch -M main
```

### create readme, push it using main, so that main is created in github
git add readme.md
git commit -m "added readme"
git push -u origin main


### create branch for each ver / milestone
git checkout -b "branchV2"
-no gap in br name

**check branch
git branch

### push all code to github now
git add .
git commit -m "all v2 code pushed 2 gthb"
git push -u origin branchV2


** extra info ---
** to see branches
>> git branch  

** to see all files / folders in curr dir
>> ls


## how to use temp GitHub... push cd to tempgithub. .. 

shreyasHm@shreyas1apzn MINGW64 /f/INTERN_PROJ/TripNestAgFtd_ShreyasBkd/TripNestProjVer2_M2 (main)
$ git remote add origin git@github-temp:skyInfApx/TripNestProjAgSy.git

shreyasHm@shreyas1apzn MINGW64 /f/INTERN_PROJ/TripNestAgFtd_ShreyasBkd/TripNestProjVer2_M2 (main)
$ git remote -v
origin  git@github-temp:skyInfApx/TripNestProjAgSy.git (fetch)
origin  git@github-temp:skyInfApx/TripNestProjAgSy.git (push)

chgd... to temp github ac


-------------

## 📋 Gist - 4. Quick Verification Commands (Quality Check)

If you ever want to verify that your code compiles cleanly with 0 errors:
bash

# Frontend Lint Check (0 errors, 0 warnings)
cd code_FrontEnd
npm run lint

# Frontend Production Build Test (Builds in < 300ms)
npm run build

# Backend Compile Check (BUILD SUCCESS)
cd ../code_Backend
.\mvnw.cmd compile


---------------


## ****** misc git temp ac
----------
## how to use temp GitHub... push cd to tempgithub. .. 

shreyasHm@shreyas1apzn MINGW64 /f/INTERN_PROJ/TripNestAgFtd_ShreyasBkd/TripNestProjVer2_M2 (main)
$ git remote add origin git@github-temp:skyInfApx/TripNestProjAgSy.git

shreyasHm@shreyas1apzn MINGW64 /f/INTERN_PROJ/TripNestAgFtd_ShreyasBkd/TripNestProjVer2_M2 (main)
$ git remote -v
origin  git@github-temp:skyInfApx/TripNestProjAgSy.git (fetch)
origin  git@github-temp:skyInfApx/TripNestProjAgSy.git (push)

chgd...



-------------
## actual ... in main github
git remote add origin https://github.com/alpha1zln/TripNestProjAgSy.git ... sth like ths .. 

-----------
## chg master to main

shreyasHm@shreyas1apzn MINGW64 /f/INTERN_PROJ/TripNestAgFtd_ShreyasBkd/TripNestProjVer2_M2 (master)
```
$ git branch -M main
```

------------------------------------------------------

-----------------------

git remote add origin https://github.com/alpha1zln/TripNestProjAgSy.git      // add repo to local folder
// if already readme created in repo , then clone then create files in pc

git add .                        // add all files 
git branch -M main         	// chng master to main
git checkout -b branchNew       // create a new branch
git branch branchNew		// switch to new branch


git push -u origin main  	// push to main
git push -u origin branchNew 	// push to branch


-----------------


******************

^^ Here are the exact step-by-step commands for MySQL, formatted just like your PostgreSQL example:
Step: Open MySQL and create the new database


1. Open MySQL CLI from Terminal / Command Prompt
bash
//	   mysql -u root -p
(Press Enter, then type your MySQL root password and press Enter)



2. Once inside MySQL (mysql> prompt):
sql
-- 1. Create the new database for Smart Clinic
// 	CREATE DATABASE cms;
-- (Or recommended safe practice: only create if it doesn't already exist)
//   	CREATE DATABASE IF NOT EXISTS cms;



sql
-- 2. Verify it was created (Equivalent to PostgreSQL's \l or \list)
// 	SHOW DATABASES;
sql



-- 3. Connect / Switch to the new database (Equivalent to PostgreSQL's \c cms)
//	USE cms;



sql
-- 4. Verify which database you are currently connected to
SELECT DATABASE();


**
💡 Quick Comparison Cheat Sheet: 			PostgreSQL vs MySQL

Action				PostgreSQL (psql)		MySQL (mysql)
Login to CLI			psql -U postgres		mysql -u root -p
Create Database			CREATE DATABASE cms;		CREATE DATABASE cms;
List Databases			\l or \list			SHOW DATABASES;
Connect 			/ Switch DB			\c cms	USE cms;
Check Current DB		Look at prompt (cms=#)		SELECT DATABASE();
List Tables			\dt				SHOW TABLES;
Describe Table Schema		\d doctors			DESCRIBE doctors; or DESC doctors;
Exit CLI			\q				exit; or quit;



*********************************












*********************************************************************

mysqlpw
1234
psql
db1234
GitHub
1234
mongodb



*********************************************************************

*******************
extra 
*******************


********* reset MySQL password******************************************

^^^^  i cant rembr my mysql passwrd ... what to do
Don't worry, this happens all the time! Because you are on Windows and have MySQL 8.0, you have two very easy ways to reset it.
^^^^^^


Method 1: The Easiest GUI Way (Recommended — 2 Minutes)
Since you installed MySQL via the official Windows Installer, you can reset the password directly from the wizard:


Press the Windows Key on your keyboard and search for:
  👉 MySQL Installer - Community (or MySQL Installer)  
Open it (click Yes if prompted for Admin permission).

In the list of installed products, find MySQL Server 8.0.

On the right side, click Reconfigure. 

Click Next until you reach the Accounts and Roles screen.
 
Type your new password (e.g. root123 or Admin@123) in the MySQL Root Password and Confirm Password boxes.
  
Click Next 
→→ Execute 
→→ Finish.

  
Your root password is now reset and the service is started!





****************************************

# how to check collection name, doc list, how to add, etc 

# how to check db name, table / doc name, collections name, all docs list in collection in  mongodb cli ?

Here is your complete guide to checking databases, collections (tables), and documents (rows) inside the MongoDB CLI (mongosh):

## 1. Check All Database Names

(MySQL equivalent: SHOW DATABASES;)
javascript
- show dbs

Example Output:
text
admin          40.00 KiB
config        108.00 KiB
local          72.00 KiB
prescriptions  72.00 KiB   <-- Our clinic database


## 2. Check Currently Active Database Name

(MySQL equivalent: SELECT DATABASE();)
First switch to your database:
javascript
- use prescriptions

Then simply type:
javascript
- db

Output:
text
prescriptions



## 3. Check All Collection Names (Tables)

(MySQL equivalent: SHOW TABLES;)
javascript
- show collections

Example Output:
text
prescriptions


## 4. Check All Documents in a Collection (Rows)

(MySQL equivalent: SELECT * FROM prescriptions;)
javascript
- db.prescriptions.find()

To format the output with clean indentations and colors, use .pretty():
javascript
- db.prescriptions.find().pretty()



## 5. Check Just the First / Sample Document

(MySQL equivalent: SELECT * FROM prescriptions LIMIT 1;)
javascript
- db.prescriptions.findOne()

Example Output:
javascript
{
  _id: ObjectId('6807dd712725f013281e7201'),
  patientName: 'John Smith',
  appointmentId: 51,
  medication: 'Paracetamol',
  dosage: '500mg',
  doctorNotes: 'Take 1 tablet every 6 hours.',
  _class: 'com.project.back_end.models.Prescription'
}


## 6. Count Total Documents in a Collection

(MySQL equivalent: SELECT COUNT(*) FROM prescriptions;)
javascript
- db.prescriptions.countDocuments()

Output: text
24


## 7. Filter Specific Documents

(MySQL equivalent: SELECT * FROM prescriptions WHERE patientName = 'John Smith';)
javascript
- db.prescriptions.find({ patientName: "John Smith" })

----

## 💡 Quick Cheat Sheet:                MySQL         vs                  MongoDB CLI
```
What do you want to see?	        In MySQL (mysql)            	In MongoDB (mongosh)
All Databases	                    SHOW DATABASES;	                    show dbs
Current Database	                SELECT DATABASE();	                db
Switch Database	                    USE cms;	                        use prescriptions
All Tables / Collections	        SHOW TABLES;    	                show collections
All Records / Documents	            SELECT * FROM prescriptions;    	db.prescriptions.find()
One Record / Document	            SELECT * FROM ... LIMIT 1;      	db.prescriptions.findOne()
Total Count	                        SELECT COUNT(*) FROM ...;	        db.prescriptions.countDocuments()
Exit CLI                        	exit;                           	exit
```


---






****************************************




^^^^ mongodb - how to add data ^^^^^


how to add in  mongodb, explain in steps - gui , cli both ways

Here are the step-by-step instructions for adding the sample prescription data into MongoDB using both the GUI (MongoDB Compass) and the CLI (mongosh).

Method 1: The GUI Way (Using MongoDB Compass)

MongoDB Compass is the official visual desktop tool for MongoDB (installed automatically when you install MongoDB).

Step 1: Open MongoDB Compass & Connect

  
Search for MongoDB Compass in your Windows Start menu and open it.

  
In the connection box at the top, leave the default:
  
text
mongodb://localhost:27017

  

  
Click the green Connect button.

  
Step 2: Create the Database & Collection

  
On the left sidebar, click the + (Create Database) button.

  
Enter:
  
  

  
Database Name: prescriptions

  
Collection Name: prescriptions

  

  
Click Create Database.

  
Click on prescriptions in the left sidebar to open the collection.

  
Step 3: Insert the Data (Two ways inside Compass)
🚀 Super-Fast Way (Using Embedded Shell at bottom of Compass):

  
Look at the very bottom bar of Compass—you will see a button labeled >_ MONGOSH.

  
Click it to slide open the built-in terminal!

  
Type:
  
javascript
use prescriptions

  

  
Copy the entire db.prescriptions.insertMany([ ... ]); block from DATA-ADD.MD (lines 686–903).

  
Paste it into the shell and hit Enter!

  
🖱️ Visual UI Way (Using "Add Data"):

  
In the main view of the prescriptions collection, click the green button Add Data 
→
→ select Insert document.

  
In the popup dialog, click the {} (JSON mode) view icon in the top right.

  
Paste the documents array [ { ... }, { ... } ] inside.

  
Click Insert.

  
Method 2: The CLI Way (Using mongosh / PowerShell)
Step 1: Open Terminal & Launch MongoDB Shell

Open PowerShell or Command Prompt:

bash
mongosh

(If you see test>, you are connected!)

Step 2: Switch to the prescriptions Database
javascript
use prescriptions

(Output: switched to db prescriptions)

Step 3: Paste the insertMany Command

Copy and paste the command from DATA-ADD.MD:

javascript
db.prescriptions.insertMany([
  {
    "_id": ObjectId("6807dd712725f013281e7201"),
    "patientName": "John Smith",
    "appointmentId": 51,
    "medication": "Paracetamol",
    "dosage": "500mg",
    "doctorNotes": "Take 1 tablet every 6 hours.",
    "_class": "com.project.back_end.models.Prescription"
  },
  {
    "_id": ObjectId("6807dd712725f013281e7202"),
    "patientName": "Emily Rose",
    "appointmentId": 52,
    "medication": "Aspirin",
    "dosage": "300mg",
    "doctorNotes": "Take 1 tablet after meals.",
    "_class": "com.project.back_end.models.Prescription"
  },
  {
    "_id": ObjectId("6807dd712725f013281e7203"),
    "patientName": "Michael Jordan",
    "appointmentId": 53,
    "medication": "Ibuprofen",
    "dosage": "400mg",
    "doctorNotes": "Take 1 tablet every 8 hours.",
    "_class": "com.project.back_end.models.Prescription"
  }
  // ... and the rest of the 50 items from DATA-ADD.MD
]);

Hit Enter. You will see:

text
{
  acknowledged: true,
  insertedIds: {
    '0': ObjectId('6807dd712725f013281e7201'),
    '1': ObjectId('6807dd712725f013281e7202'),
    ...
  }
}
🔍 Step 4: Verification (Run in either Compass or CLI)

To verify the documents were successfully inserted:

javascript
// Check total count (should be 50)
db.prescriptions.countDocuments()
// View one sample document
db.prescriptions.findOne()
// Find prescriptions for a specific patient
db.prescriptions.find({ patientName: "John Smith" })




---------------------------------------------------------------------
---------------------------------------------------------------------

