package com.mavericks.assignment;

import java.io.IOException;
import java.util.ArrayList;

import com.mavericks.assignment.pojo.FamilyTree;

import junit.framework.TestCase;

public class FamilyHierarchyTest extends TestCase {
	private FamilyHierarchy familyHierarchy = new FamilyHierarchy();
	private ArrayList<FamilyTree> family = new ArrayList<FamilyTree>();
	private String result = null;
	
	public static void main(String[] a) {
		junit.textui.TestRunner.run(FamilyHierarchyTest.class);
	}
	
	//Setup (Pre-Run) to initialize/load the file content (Family Hierarchy) into memory.
	protected void setUp() {
		try {
			familyHierarchy.loadData();
		} catch(IOException e) {
			System.out.println("IOException: " + e);
		} catch(Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
	//Test Case: Input Pattern: Person=Alex Relation=Brothers
	public void testRelationBrothers() {
		result = new String();
		family = familyHierarchy.interceptCommands("Person", "Alex", "Relation", "Brothers");
		for(FamilyTree e : family) {
			result+= e.getName();
		}
		assertTrue(result.equalsIgnoreCase("JOHNJOE"));
	}
	
	//Test Case: Input Pattern: Person=Alex Relation=Sisters
	public void testRelationSisters() {
		result = new String();
		family = familyHierarchy.interceptCommands("Person", "Alex", "Relation", "Sisters");
		for(FamilyTree e : family) {
			result+= e.getName();
		}
		assertTrue(result.equalsIgnoreCase("NISHA"));
	}
	
	//Test Case: Input Pattern: Person=Alex Relation=Siblings
	public void testRelationSiblings() {
		result = new String();
		family = familyHierarchy.interceptCommands("Person", "Alex", "Relation", "Siblings");
		for(FamilyTree e : family) {
			result+= e.getName();
		}
		assertTrue(result.equalsIgnoreCase("JOHNJOENISHA"));
	}
	
	public void testRelationFather() {
		result = new String();
		family = familyHierarchy.interceptCommands("Person", "Alex", "Relation", "Father");
		for(FamilyTree e : family) {
			result+= e.getName();
		}
		assertTrue(result.equalsIgnoreCase("EVAN"));
	}
	
	public void testRelationMother() {
		result = new String();
		family = familyHierarchy.interceptCommands("Person", "Alex", "Relation", "Mother");
		for(FamilyTree e : family) {
			result+= e.getName();
		}
		assertTrue(result.equalsIgnoreCase("DIANA"));
	}
	
	//Test Case: Input Pattern: Husband=Bern Wife=Julia
	public void testAddWife() {
		result = new String();
		family = familyHierarchy.interceptCommands("Husband", "Bern", "Wife", "Julia");
		for(FamilyTree e : family) {
			result+= e.getName();
		}
		assertTrue(result.equalsIgnoreCase("JULIA"));
	}
	
	//Test Case: Input Pattern: Mother=Zoe Son=Boris
	public void testAddSon() {
		result = new String();
		family = familyHierarchy.interceptCommands("Mother", "Zoe", "Son", "Boris");
		for(FamilyTree e : family) {
			result+= e.getName();
		}
		assertTrue(result.equalsIgnoreCase("BORIS"));
	}
	
	//Test Case: Input Pattern: Mother=Zoe Daughter=Anika
	public void testAddDaugher() {
		result = new String();
		family = familyHierarchy.interceptCommands("Mother", "Zoe", "Daughter", "Anika");
		for(FamilyTree e : family) {
			result+= e.getName();
		}
		assertTrue(result.equalsIgnoreCase("ANIKA"));
	}
}
