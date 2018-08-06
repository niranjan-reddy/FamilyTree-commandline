Mavericks Developer Assignment 2
==================================================

Programming Language used:	Java
IDE Used:					Spring Tool Suite (Eclipse) with Maven
Libraries Used:				java.util.*, java.io.*
Test Library:				jUnit
3rd party Libraries:		NONE
Preloaded Family Tree:		File "data/ancestry.info". Values are separated using blank spaces. Data storage pattern is "REFID NAME GENDER FATHER_REFID MOTHER_REFID SPOUSE_REFID". This was easy way to handle small quantity of data, hence no use of DB (RDBMS/NOSQL) is made.

Setup Instructions:
=====================
1. Unzip the archive into a fresh new folder/directory.
2. Open Eclipse IDE.
3. Import the project into Eclipse IDE.
4. Navigate to file "FamilyHierarchy.java".
5. Right-click the file and click "Run As" > "Java Application".
6. This should launch the application in the inbuilt Terminal/Console

Alternate Approach:
=====================
1. Unsip the archive to any new directory.
2. Open Terminal/Console/CMD.
3. Navigate to the folder/directory "src/main/java".
4. Compile the source code using "JAVAC" command.
5. Execute the application by executing "JAVA com.mavericks.assignment.FamilyHierarchy".

Test Execution:
=====================
Several test cases are prepared and are present inside the file "FamilyHierarchyTest.java".
All basic tests are supplied (8 nos), however the application can perform many more operations.

Execute the Test in the same manner as the above approach. Since this consists PSVM, should work outside the Eclipse IDE as well.

Note:
=====================
Though I have built this POC using Spring Tool Suite and Maven, this project is not Mavenized entirely, hence porting to non-Maven project should be as easy as moving the files inside "src/main/java" and "src/test/java" into a separate basic Java project's "src" folder/directory.







