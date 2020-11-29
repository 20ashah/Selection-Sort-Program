/*
 Arjun Shah
 E Block
 2/15/19
 Program Description:
 This program allows the user to enter test, quiz, and homework scores for 
 4 quarters. This user can then print out the students in alphabetical order, 
 by final average, or unsorted for each quarter. This program uses selection 
 sort to sort the array. The user can then view the statistics for each quarter, 
 which include the mean, median, mode, and standard deviation.
*/

import BreezySwing.*;
import java.util.*;
import javax.swing.*;
import java.io.IOException;
import java.text.*;

public class SelectionGUI extends GBFrame {

	// declaring GUI components to be placed in the window
	private JMenuItem sortName, sortAv1, sortAv2, sortAv3, sortAv4;
	private JMenuItem quit, stats1, stats2, stats3, stats4, help;
	private JMenuItem printName, printQ1, printQ2, printQ3, printQ4;
	private JLabel nameL, testL, quizL, hwL;
	private JTextField nameF, testF, quizF;
	private IntegerField hwF;
	private JComboBox<String> quarter;
	private JButton addStudent, setQuarter;

	// contains the list of all students with their information 
	private AllStudents students = new AllStudents();

	// constructor for adding GUI components to the window
	public SelectionGUI() {
		// adding the GUI components in the top menu bar
		quit = addMenuItem("File", "Quit");
		help = addMenuItem("File","Help");
		sortName = addMenuItem("Sort By", "Name");
		sortAv1 = addMenuItem("Sort By", "Final Average - Q1");
		sortAv2 = addMenuItem("Sort By", "Final Average - Q2");
		sortAv3 = addMenuItem("Sort By", "Final Average - Q3");
		sortAv4 = addMenuItem("Sort By", "Final Average - Q4");
		stats1 = addMenuItem("Statistics", "Quarter 1");
		stats2 = addMenuItem("Statistics", "Quarter 2");
		stats3 = addMenuItem("Statistics", "Quarter 3");
		stats4 = addMenuItem("Statistics", "Quarter 4");
		printName = addMenuItem("Print", "Names");
		printQ1 = addMenuItem("Print", "Quarter 1");
		printQ2 = addMenuItem("Print", "Quarter 2");
		printQ3 = addMenuItem("Print", "Quarter 3");
		printQ4 = addMenuItem("Print", "Quarter 4");

		// adding GUI label components
		nameL = addLabel("Name", 1, 1, 1, 1);
		testL = addLabel("Tests", 2, 1, 1, 1);
		quizL = addLabel("Quizzes", 3, 1, 1, 1);
		hwL = addLabel("HW Average", 4, 1, 1, 1);

		// adding GUI text field components
		nameF = addTextField("", 1, 2, 1, 1);
		testF = addTextField("", 2, 2, 1, 1);
		quizF = addTextField("", 3, 2, 1, 1);
		hwF = addIntegerField(0, 4, 2, 1, 1);

		// adding GUI button components
		setQuarter = addButton("Set Quarter Averages", 6, 1, 1, 1);
		addStudent = addButton("Add Student", 7, 1, 1, 1);

		// adding and setting the combo box to the window
		quarter = addComboBox(5, 2, 1, 1);
		quarter.addItem("Quarter 1");
		quarter.addItem("Quarter 2");
		quarter.addItem("Quarter 3");
		quarter.addItem("Quarter 4");
		hwF.setText("");

		controlMenu(false); // makes sure results buttons are disabled
	}

	// creating new arrays for the student data the user enters in
	int[][] tests = {{-1},{-1},{-1},{-1}};
	int[][] quizzes = {{-1},{-1},{-1},{-1}};
	int[] hw = {-1,-1,-1,-1};

	// method for performing actions when buttons are clicked
	public void buttonClicked(JButton button) {
		if (button == addStudent) { // if user clicks the add student button
			if (students.getNumStudents() == 15) { // check if list is full
				messageBox("There is no room left for this student.");
			} else {
				// checking if any quarter has not been entered in
				for (int i=0;i<4;i++) {
					if (tests[i][0] == -1) { // enter those quarters as 0's
						tests[i] = new int[1];
					}
				}
				// create a new student object with the information entered by user
				StudentInfo s = new StudentInfo(tests, quizzes, hw, nameF.getText());
				students.add(s);
				// reset the information arrays for the next student
				tests = new int[4][1];
				quizzes = new int[4][1];
				// filling the arrays back with -1
				for (int i=0;i<4;i++) {
					Arrays.fill(tests[i],-1);
					Arrays.fill(quizzes[i],-1);
				}
				hw = new int[4]; Arrays.fill(hw,-1);
				controlMenu(true); // enable results menu bar
			}
			// clear all of the input fields
			nameF.setText("");
			testF.setText("");
			quizF.setText("");
			hwF.setText("");
		} else if (button == setQuarter) { // if user clicks the set quarter button
			try {
				// create arrays for the information entered by the user
				int[] test = parse(testF.getText());
				int[] quiz = parse(quizF.getText());
				int homework = hwF.getNumber();

				// error checking max number of tests and quizzes
				if (test.length > 5 || quiz.length > 8) {
					throw new IOException();
				}

				// error checking to see if the person's name is blank
				if (nameF.getText().trim().equals("")) {
					throw new IllegalArgumentException();
				}
				
				// error checking the homework input that the user entered
				if (!hwF.isValid() || hwF.getNumber() < 0 || hwF.getNumber() > 100) {
					throw new IllegalArgumentException();
				}
				
				if (quarter.getSelectedItem().equals("Quarter 1")) { // if user chose quarter 1
					// set the first quarter of the 2d array
					tests[0] = test;
					quizzes[0] = quiz;
					hw[0] = homework;
				} else if (quarter.getSelectedItem().equals("Quarter 2")) { // if user chose quarter 2
					// set the second quarter of the 2d array
					tests[1] = test;
					quizzes[1] = quiz;
					hw[1] = homework;
				} else if (quarter.getSelectedItem().equals("Quarter 3")) { // if user chose quarter 3
					// set the third quarter of the 2d array
					tests[2] = test;
					quizzes[2] = quiz;
					hw[2] = homework;
				} else if (quarter.getSelectedItem().equals("Quarter 4")) { // if user chose quarter 4
					// set the fourth quarter of the 2d array
					tests[3] = test;
					quizzes[3] = quiz;
					hw[3] = homework;
				}

				// reset all input fields except the name
				testF.setText("");
				quizF.setText("");
				hwF.setText("");

			} catch (NumberFormatException n) { // if there was a parsing error
				messageBox("Invalid list of scores! Try again!", 400, 150);
			} catch (IllegalArgumentException e) { // if there was a homework parsing issue
				messageBox("Invalid inputs! Try again!", 400, 150);
			} catch (IOException e) { // if there was an invalid number of scores added
				messageBox("You are limited to 5 tests / 8 quizzes a quarter!", 400, 150);
			} catch (Exception e) { // if there was invalid grades added
				messageBox("Grades must be from 0-100!");
			}
		}

	}

	// method for when menu bar options are selected
	public void menuItemSelected(JMenuItem item) {
		if (item == sortName) { // if user clicks sort by name
			// sort the list alphabetically and print it out
			students.sortByName();
			messageBox(students.printNamesSorted(), 700, 200);
		} else if (item == sortAv1) { // if user clicks sort by Q1
			// sort the list by Q1 average and print it out
			students.sortByQuarter(0);
			messageBox(students.printAllSorted(0), 700, 200);
		} else if (item == sortAv2) { // if user clicks sort by Q2
			// sort the list by Q2 average and print it out
			students.sortByQuarter(1);
			messageBox(students.printAllSorted(1), 700, 200);
		} else if (item == sortAv3) { // if user clicks sort by Q3
			// sort the list by Q3 average and print it out
			students.sortByQuarter(2);
			messageBox(students.printAllSorted(2), 700, 200);
		} else if (item == sortAv4) { // if user clicks sort by Q4
			// sort the list by Q4 average and print it out
			students.sortByQuarter(3);
			messageBox(students.printAllSorted(3), 700, 200);
		} else if (item == quit) { // if user clicks the quit program
			System.exit(0); // exit program
		} else if (item == stats1) { // if user clicks statistics for Q1
			messageBox(printStats(0), 350, 250);
		} else if (item == stats2) { // if user clicks statistics for Q2
			messageBox(printStats(1), 350, 250);
		} else if (item == stats3) { // if user clicks statistics for Q3
			messageBox(printStats(2), 350, 250);
		} else if (item == stats4) { // if user clicks statistics for Q4
			messageBox(printStats(3), 350, 250);
		} else if (item == printName) { // if user wants to print names unsorted
			messageBox(students.printNamesUnsorted(), 700, 200);
		} else if (item == printQ1) { // if user wants to print unsorted Q1
			messageBox(students.printAllUnsorted(0), 700, 200);
		} else if (item == printQ2) { // if user wants to print unsorted Q2
			messageBox(students.printAllUnsorted(1), 700, 200);
		} else if (item == printQ3) { // if user wants to print unsorted Q3
			messageBox(students.printAllUnsorted(2), 700, 200);
		} else if (item == printQ4) { // if user wants to print unsorted Q4
			messageBox(students.printAllUnsorted(3), 700, 200);
		} else if (item == help) { // if user wants help with instructions
			messageBox("Hi! Welcome to the Selection Sort Program!\nTo start, "
					+ "add students into the database by\nproviding a list of "
					+ "quizzes, tests, and homework\naverages for 4 quarters! "
					+ "Any quarters you leave\nblank will count as a 0. After "
					+ "you finish adding\neveryone you want, you can click on "
					+ "various buttons\nto see certain averages and stats, and "
					+ "even an\nunsorted and sorted list of the students! Enjoy!",400,300);
		}
	}

	// parsing the string into individual numbers
	public int[] parse(String data) throws Exception {
		// list of integers to hold scores
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		String number = "";
		// looping through each character of string
		for (int i = 0; i < data.length(); i++) {
			if (data.charAt(i) != ',') { // if character not a comma
				number += "" + data.charAt(i); // add it to the number
			} else if (data.charAt(i) != ' ') { // if a comma is reached
				// add the whole number to the array of numbers
				int add = Integer.parseInt(number.trim());
				// if there is an invalid score, throw an error
				if (add > 100 || add < 0) {
					throw new Exception();
				}
				// add number to list and reset number
				numbers.add(add);
				number = "";
			}
		}

		int add = Integer.parseInt(number.trim()); // number to be added to array
		// add last number reached to the array
		if (add > 100 || add < 0) { // if there is an invalid score, throw an error
			throw new Exception();
		}
		numbers.add(add);

		// returning the list in an array form
		int[] numberArray = new int[numbers.size()];
		// looping through the list of numbers
		for (int i = 0; i < numbers.size(); i++) {
			// add each number from list to array
			numberArray[i] = numbers.get(i);
		}
		return numberArray;
	}

	// printing out the statistics for a particular quarter
	public String printStats(int quarter) {
		DecimalFormat decform = new DecimalFormat("0.00"); // for rounding
		// creating a new statistics object for calculating statistics
		Statistics stats = new Statistics(students, quarter);
		String output = "";
		// calculate the output the mean, median, mode, and standard deviation
		output += "Mean: " + decform.format(stats.getMean(quarter));
		output += "\nMedian: " + decform.format(stats.getMedian(quarter));
		output += "\nMode: " + stats.getMode(quarter);
		output += "\nStandard Deviation: " + decform.format(stats.getSD(quarter));
		return output;
	}

	// controlling the accessibility of the menu items 
	public void controlMenu(boolean visible) {
		sortName.setEnabled(visible);
		sortAv1.setEnabled(visible);
		sortAv2.setEnabled(visible);
		sortAv3.setEnabled(visible);
		sortAv4.setEnabled(visible);
		stats1.setEnabled(visible);
		stats2.setEnabled(visible);
		stats3.setEnabled(visible);
		stats4.setEnabled(visible);
		printName.setEnabled(visible);
		printQ1.setEnabled(visible);
		printQ2.setEnabled(visible);
		printQ3.setEnabled(visible);
		printQ4.setEnabled(visible);
	}

	// main method
	public static void main(String[] args) {
		SelectionGUI window = new SelectionGUI(); // creating new window
		window.setSize(650, 300); // setting size of the window
		window.setVisible(true); // making window visible
		window.setTitle("Selection Sort Program"); // setting title of the window

	}
}
