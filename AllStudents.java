/*
 This class contains an array of student objects. This class contains methods to 
 sort the array of students based on several different factors, such as name, 
 quarter 1 grade, quarter 2 grade, quarter 3 grade, or quarter 4 grade. This class
 also prints out the list of students in non-sorted order
*/

import java.util.*;

public class AllStudents {

	// list of students objects, both sorted and unsorted
	private ArrayList<StudentInfo> students = new ArrayList<StudentInfo>();
	private ArrayList<StudentInfo> unsorted = new ArrayList<StudentInfo>();

	// method for getting a student at a given index
	public StudentInfo getStudent(int index) {
		return students.get(index);
	}

	// adding a student to both arrays
	public void add(StudentInfo s) {
		students.add(s); // adding student to sorted list
		unsorted.add(s); // adding student to unsorted list
	}

	// getting the number of students in the list
	public int getNumStudents() {
		return students.size();
	}

	// printing out the sorted list based from a certain quarter
	public String printAllSorted(int quarter) {
		String output = students.get(0).getName() + "(" + students.get(0).finalAverage(quarter) + ")";
		// looping through the sorted array of students
		for (int i = 1; i < students.size(); i++) {
			// printing out each student and their information
			output += ", " + students.get(i).getName() + "(" + students.get(i).finalAverage(quarter) + ")";
		}
		return output;
	}

	// printing out just the names of the students in the sorted list
	public String printNamesSorted() {
		String output = students.get(0).getName();
		// looping through the sorted array of students
		for (int i = 1; i < students.size(); i++) {
			// printing out each student's name
			output += ", " + students.get(i).getName();
		}
		return output;
	}

	// printing out just the names of the students in the unsorted list
	public String printNamesUnsorted() {
		String output = unsorted.get(0).getName();
		// looping through the unsorted array of students
		for (int i = 1; i < unsorted.size(); i++) {
			// printing out each student's name
			output += ", " + unsorted.get(i).getName();
		}
		return output;
	}

	// printing out the unsorted list based from a certain quarter
	public String printAllUnsorted(int quarter) {
		String output = unsorted.get(0).getName() + "(" + unsorted.get(0).finalAverage(quarter) + ")";
		// looping through the unsorted list of students
		for (int i = 1; i < unsorted.size(); i++) {
			// printing out each student and their information
			output += ", " + unsorted.get(i).getName() + "(" + unsorted.get(i).finalAverage(quarter) + ")";
		}
		return output;
	}

	// swapping two elements in a list
	private void swap(int first, int second) {
		StudentInfo temp = students.get(first); // setting first element to a temporary variable
		students.set(first, students.get(second)); // setting second element to first
		students.set(second, temp); // setting second element to temporary
	}

	// sorting the array of students in alphabetical order
	public void sortByName() {
		// looping through the array of students
		for (int i = 0; i < students.size(); i++) {
			int min = findMinName(i); // finding minimum value starting from index i
			swap(i, min); // swap the minimum value with current index
		}
	}

	// sorting the array of students backwards by a certain order
	public void sortByQuarter(int quarter) {
		// looping through the array of students
		for (int i = 0; i < students.size(); i++) {
			int max = findMaxQuarter(i, quarter); // finding the max value starting from index i
			swap(i, max); // swap the maximum value with current index
		}
	}

	// finding the maximum grade in a quarter starting from a certain index
	private int findMaxQuarter(int start, int quarter) {
		StudentInfo max = students.get(start); // set maximum to first element
		int maxIndex = start; // set maximum index to first index
		// looping through the array of students
		for (int i = start; i < students.size(); i++) {
			// if final average greater than max
			if (students.get(i).compareFinalAverage(max, quarter) > 0) {
				// set new maximum element and index
				max = students.get(i);
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	// finding the minimum name starting from a certain index
	private int findMinName(int start) {
		StudentInfo min = students.get(start); // set minimum name to first element
		int minIndex = start; // set minimum index to first index
		// looping through the array of students
		for (int i = start; i < students.size(); i++) {
			// if the name is alphabetically less than minimum
			if (students.get(i).compareName(min) < 0) {
				// set new minimum element and index
				min = students.get(i);
				minIndex = i;
			}
		}
		return minIndex;
	}
}
