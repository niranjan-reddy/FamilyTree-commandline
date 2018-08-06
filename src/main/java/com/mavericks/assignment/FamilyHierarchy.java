package com.mavericks.assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mavericks.assignment.pojo.FamilyTree;

public class FamilyHierarchy {
	static Map<Integer, FamilyTree> families;
	static FamilyTree root;
	final static String dir = System.getProperty("user.dir");
	final static String path = dir.replace("/", "\\\\") + "\\data\\";
	final static String file = path + "ancestry.info";
	    
	 public static void main(String[] args) throws IOException {
		 	loadData();
	        System.out.println("Please enter QUIT or Q to Exit!");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	        System.out.print("Input: ");
	        String userInput;
	        do {
	            userInput = (String) reader.readLine();
		        handleInput(userInput);
	          } while(!userInput.equalsIgnoreCase("QUIT") || !userInput.equalsIgnoreCase("Q"));
	 }
	 
	 //Load File content (Family Tree/Hierarchy) into memory. 
	 public static void loadData() throws IOException {
		    families = new HashMap<Integer, FamilyTree>();
	        readDataAndCreateMap();
	 }
	 
	 //Handles input supplied by user in the form "KEY1=VALUE1 KEY2=VALUE2", e.g., "Person=Alex Relation=Brothers" 
	 public static void handleInput(String userInput) {
		 String firstKey;
		 String firstValue;
		 String secondKey;
		 String secondValue;
		 ArrayList<FamilyTree> family = new ArrayList<FamilyTree>();
		 String[] inputs = userInput.split(" ");
		 if(userInput.equalsIgnoreCase("Q") || userInput.equalsIgnoreCase("QUIT"))
			 System.out.println("Have a Great Day!!!");
		 else if(inputs.length != 2) {
			 System.out.println("Output: Please check your input.");
		 } else {
			 String[] keyVal1 = inputs[0].split("=");
			 String[] keyVal2 = inputs[1].split("=");
			 if(keyVal1.length !=2 || keyVal2.length !=2)
				 System.out.println("Output: Please check your input.");
			 else {
				 firstKey = keyVal1[0];
				 firstValue = keyVal1[1];
				 secondKey = keyVal2[0];
				 secondValue = keyVal2[1];
				 family = interceptCommands(firstKey, firstValue, secondKey, secondValue);
				 if(firstKey.equalsIgnoreCase("Person")) {
					System.out.print("Output: " + secondValue + "=");
					StringBuffer nameList = new StringBuffer();
					for(FamilyTree e : family) {
						nameList.append(e.getName() + ",");
					}
					System.out.println(nameList.substring(0, nameList.length() - 1));
				 }
			 }
		 }
		 System.out.print("Input: ");
	 }
	 
	 //Does appropriate operation on the FamilyTree in memory based on the input supplied by user.
	 public static ArrayList<FamilyTree> interceptCommands(String firstKey, String name, String secondKey, String secondValue) {
		 FamilyTree details = new FamilyTree();
		 ArrayList<FamilyTree> subTree = new ArrayList<FamilyTree>();
		 for (FamilyTree e : families.values()) {
           if (e.getName().equalsIgnoreCase(name)) {
        	   details = e;
        	   break;
           }
		 }
		 
		 //When SEARCH operation is carried out (KEY1 = "Person"), this section/sub-sections do the necessary operations.
		 if(firstKey.equalsIgnoreCase("Person")) {
			 //KEY2 is expected to be "Relation" to qualify the SEARCH operation.
			 if(secondKey.equalsIgnoreCase("Relation")) {
				 if(secondValue.equalsIgnoreCase("Brothers")) {
					 for (FamilyTree e : families.values()) {
			           if (e.getFatherRefId() == details.getFatherRefId() && e.getGender().equalsIgnoreCase("M") && !e.getName().equalsIgnoreCase(name)) {
			        	   subTree.add(e);
			           }
					 }
					 return subTree;
				 } else if(secondValue.equalsIgnoreCase("Sisters")) {
					 for (FamilyTree e : families.values()) {
			           if (e.getFatherRefId() == details.getFatherRefId() && e.getGender().equalsIgnoreCase("F") && !e.getName().equalsIgnoreCase(name)) {
			        	   subTree.add(e);
			           }
					 }
					 return subTree;
				 } else if(secondValue.equalsIgnoreCase("Siblings")) {
					 for (FamilyTree e : families.values()) {
			           if (e.getFatherRefId() == details.getFatherRefId() && !e.getName().equalsIgnoreCase(name)) {
			        	   subTree.add(e);
			           }
					 }
					 return subTree;
				 } else if(secondValue.equalsIgnoreCase("Father")) {
					 for (FamilyTree e : families.values()) {
			           if (e.getRefId() == details.getFatherRefId()) {
			        	   subTree.add(e);
			        	   break;
			           }
					 }
					 return subTree;
				 } else if(secondValue.equalsIgnoreCase("Mother")) {
					 for (FamilyTree e : families.values()) {
			           if (e.getRefId() == details.getMotherRefId()) {
			        	   subTree.add(e);
			        	   break;
			           }
					 }
					 return subTree;
				 } else if(secondValue.equalsIgnoreCase("Sons")) {
					 for (FamilyTree e : families.values()) {
			           if ((e.getFatherRefId() == details.getRefId() || e.getMotherRefId() == details.getRefId()) && e.getGender().equalsIgnoreCase("M")) {
			        	   subTree.add(e);
			           }
					 }
					 return subTree;
				 } else if(secondValue.equalsIgnoreCase("Daughters")) {
					 for (FamilyTree e : families.values()) {
			           if ((e.getFatherRefId() == details.getRefId() || e.getMotherRefId() == details.getRefId()) && e.getGender().equalsIgnoreCase("F")) {
			        	   subTree.add(e);
			           }
					 }
					 return subTree;
				 } else if(secondValue.equalsIgnoreCase("Children")) {
					 for (FamilyTree e : families.values()) {
			           if (e.getFatherRefId() == details.getRefId() || e.getMotherRefId() == details.getRefId()) {
			        	   subTree.add(e);
			           }
					 }
					 return subTree;
				 } else if(secondValue.equalsIgnoreCase("Wife")) {
					 for (FamilyTree e : families.values()) {
			           if (e.getSpouseRefId() == details.getRefId() && e.getGender().equalsIgnoreCase("F")) {
			        	   subTree.add(e);
			        	   break;
			           }
					 }
					 return subTree;
				 } else if(secondValue.equalsIgnoreCase("Husband")) {
					 for (FamilyTree e : families.values()) {
			           if (e.getSpouseRefId() == details.getRefId() && e.getGender().equalsIgnoreCase("M")) {
			        	   subTree.add(e);
			        	   break;
			           }
					 }
					 return subTree;
				 } else if(secondValue.equalsIgnoreCase("Spouse")) {
					 for (FamilyTree e : families.values()) {
			           if (e.getSpouseRefId() == details.getRefId()) {
			        	   subTree.add(e);
			        	   break;
			           }
					 }
					 return subTree;
				 }
			 }
		 } 
		 
		 //When ADD operation is carried out (KEY2 = "Wife"), this section/sub-sections do the necessary ADD operations.
		 else if(secondKey.equalsIgnoreCase("Wife")) {
			 if(checkDuplicate(secondValue)) {
				 System.out.println("Can't add " + secondValue + ", person already exists. Please check name (must be unique).");
			 } else
				 for (FamilyTree e : families.values()) {
					 if (e.getName().equalsIgnoreCase(name)) {
						 if (e.getGender().equalsIgnoreCase("F"))
							 System.out.println("Can't add Wife. Person is FEMALE.");
						 else if (e.getSpouseRefId() != 0)
							 System.out.println("Can't add Wife. Already present.");
						 else {
							 //append to master familytree
							 FamilyTree newMember = new FamilyTree(Integer.toString(families.size() + 1), secondValue, "F", "", "", Integer.toString(e.getRefId()));
							 addToTree(newMember);
							 //append to file
							 addToFile(newMember);
							 subTree.add(newMember);
						 }
						 break;
					 }
				 }
		 } 
		 
		//When ADD operation is carried out (KEY2 = "Husband"), this section/sub-sections do the necessary ADD operations.
		 else if(secondKey.equalsIgnoreCase("Husband")) {
			 if(checkDuplicate(secondValue)) {
				 System.out.println("Can't add " + secondValue + ", person already exists. Please check name (must be unique).");
			 } else
				 for (FamilyTree e : families.values()) {
					 if (e.getName().equalsIgnoreCase(name)) {
						 if (e.getGender().equalsIgnoreCase("M"))
							 System.out.println("Can't add Husband. Person is MALE.");
						 else if (e.getSpouseRefId() != 0)
							 System.out.println("Can't add Husband. Already present.");
						 else {
							 //append to master familytree
							 FamilyTree newMember = new FamilyTree(Integer.toString(families.size() + 1), secondValue, "M", "", "", Integer.toString(e.getRefId()));
							 addToTree(newMember);
							 //append to file
							 addToFile(newMember);
							 subTree.add(newMember);
						 }
						 break;
					 }
				 }
		 } 
		 
		//When ADD operation is carried out (KEY2 = "Son"), this section/sub-sections do the necessary ADD operations.
		 else if(secondKey.equalsIgnoreCase("Son")) {
			 if(checkDuplicate(secondValue)) {
				 System.out.println("Can't add " + secondValue + ", person already exists. Please check name (must be unique).");
			 } else
				 for (FamilyTree e : families.values()) {
					 if (e.getName().equalsIgnoreCase(name)) {
						 int fatherRefId;
						 int motherRefId;
						 if (e.getGender().equalsIgnoreCase("M")) {
							 fatherRefId = e.getSpouseRefId();
							 motherRefId = e.getRefId();
						 } else {
							 motherRefId = e.getSpouseRefId();
							 fatherRefId = e.getRefId();
						 }
						 //append to master familytree
						 FamilyTree newMember = new FamilyTree(Integer.toString(families.size() + 1), secondValue, "M", Integer.toString(fatherRefId), Integer.toString(motherRefId), "0");
						 addToTree(newMember);
						 //append to file
						 addToFile(newMember);
						 subTree.add(newMember);
						 break;
					 }
				 }
		 } 
		 
		//When ADD operation is carried out (KEY2 = "Daughter"), this section/sub-sections do the necessary ADD operations.
		 else if(secondKey.equalsIgnoreCase("Daughter")) {
			 if(checkDuplicate(secondValue)) {
				 System.out.println("Can't add " + secondValue + ", person already exists. Please check name (must be unique).");
			 } else
				 for (FamilyTree e : families.values()) {
					 if (e.getName().equalsIgnoreCase(name)) {
						 int fatherRefId;
						 int motherRefId;
						 if (e.getGender().equalsIgnoreCase("M")) {
							 fatherRefId = e.getSpouseRefId();
							 motherRefId = e.getRefId();
						 } else {
							 motherRefId = e.getSpouseRefId();
							 fatherRefId = e.getRefId();
						 }
						 //append to master familytree
						 FamilyTree newMember = new FamilyTree(Integer.toString(families.size() + 1), secondValue, "F", Integer.toString(fatherRefId), Integer.toString(motherRefId), "0");
						 addToTree(newMember);
						 //append to file
						 addToFile(newMember);
						 subTree.add(newMember);
						 break;
					 }
				 }
		 }
		 return subTree;
	 }

	 //This method reads the text file and creates the HashMap.
	 public static void readDataAndCreateMap() throws IOException {
		try {
	        FileReader fin = new FileReader(file);
			BufferedReader br = new BufferedReader(fin);
			String strLine;
			FamilyTree family = null;
			while ((strLine = br.readLine()) != null) { 
				 String[] values = strLine.split(" ");
				 try {		 
					 if (values.length > 1) {		
			             family = new FamilyTree(values[0], values[1], values[2], values[3], values[4], values[5]);
					 }
				 } catch (Exception e) {
					 family = new FamilyTree(values[0], values[1], values[2], values[3], values[4], "0");
					 System.out.println("Exception: " + e);
				 }
				 families.put(family.getRefId(), family);
				 if (family.getRefId() <= 2) {
	                 root = family;
				 }
	        }
			fin.close();
	        br.close();
		} catch (FileNotFoundException e) {
			 System.err.println("FileNotFoundException: " + e);
		} catch (IOException e) {
			 System.err.println("IOException: " + e);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	 }
	
	 //This method appends newly added family member to the HashMap or in-memory Ancestry Tree.
	 private static void addToTree(FamilyTree newMember) {
		 families.put(newMember.getRefId(), newMember);
	 }
	 
	 //This method appends the newly added family member to the text file.
	 private static void addToFile(FamilyTree newMember) {		 
		 try(FileWriter fw = new FileWriter(file, true);
			 BufferedWriter bw = new BufferedWriter(fw);
			 PrintWriter out = new PrintWriter(bw))	{
		    out.println(Integer.toString(newMember.getRefId()) + " " +
					 newMember.getName() + " " +
					 newMember.getGender() + " " +
					 Integer.toString(newMember.getFatherRefId()) + " " +
					 Integer.toString(newMember.getMotherRefId()) + " " +
					 Integer.toString(newMember.getSpouseRefId()) + " ");
		    System.out.println("Output: Welcome to the family, " + newMember.getName());
		} catch (IOException e) {
		    System.out.println("IOException : " + e);
		}
	 }
	 
	 //This method verifies the HashMap or in-memory Ancestry Tree if the person to be added is already present, since the NAME of family members is unique.
	 private static boolean checkDuplicate(String name) {
		 for (FamilyTree e : families.values())
			 if (e.getName().equalsIgnoreCase(name))
				 return true;
		 return false;
	 }
}
