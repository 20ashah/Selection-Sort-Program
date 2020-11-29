/*
 This class contains the final averages for each student for each quarter in a 2d list. 
 This class contains methods for finding certain statistical values among this data, 
 including the mean, mode, median, and standard deviation.  
*/

import java.util.*;

public class Statistics {

	// declare instance variables
	private AllStudents students; // variable to access array of students
	// containing all the final averages for ever quarter for each student
	private ArrayList<ArrayList<Integer>> finalAverages = new ArrayList<ArrayList<Integer>>();

	// constructor for setting the final averages array from the student array
	public Statistics(AllStudents s, int quarter) {
		students = s;
		for (int i = 0; i < 4; i++) {
			finalAverages.add(new ArrayList<Integer>());
		}

		// setting the quarter average for a particular quarter
		setQuarterAverages(quarter);
	}

	// setting the quarter averages for a specific quarter
	private void setQuarterAverages(int quarter) {
		// looping through the array of students
		for (int i = 0; i < students.getNumStudents(); i++) {
			// add student's final average to the final averages list
			finalAverages.get(quarter).add(students.getStudent(i).finalAverage(quarter));
		}
	}

	// getting the mean value of the list of numbers
	public double getMean(int quarter) {
		double sum = 0; // sum of all the numbers in the array
		// looping through the array of numbers
		for (int i = 0; i < finalAverages.get(quarter).size(); i++) {
			// add each number to the sum of numbers
			sum += finalAverages.get(quarter).get(i);
		}
		// divide sum by number of numbers
		return sum / finalAverages.get(quarter).size();
	}

	// getting the mode of the array of numbers
	public String getMode(int quarter) {
		ArrayList<Integer> modeList = new ArrayList<Integer>(); // list of modes
		ArrayList<Integer> occurence = getOccurance(quarter); // frequency of each number
		ArrayList<Integer> duplicates = new ArrayList<Integer>(); // for checking if no mode
		int maxOccurance = getMax(occurence); // getting max frequency in the array
		// looping through the list of the frequency array
		for (int i = 0; i < occurence.size(); i++) {
			// if frequency equals the highest frequency
			if (occurence.get(i) == maxOccurance) {
				duplicates.add(finalAverages.get(quarter).get(i));
				// add corresponding number to the mode list
				if (!modeList.contains(finalAverages.get(quarter).get(i))) { // checks if number is already there
					modeList.add(finalAverages.get(quarter).get(i));
				}
			}
		}

		// if all numbers have the same frequency
		if (duplicates.size() == finalAverages.get(quarter).size()) {
			return "This list has no mode";
		}
		return modeList.toString();
	}

	// using selection sort to sort the final averages
	private void sort(int quarter) {
		// looping through the list of averages
		for (int i = 0; i < finalAverages.get(quarter).size(); i++) {
			int min = findMinFinal(i, quarter); // finding minimum starting from i
			swap(i, min, quarter); // brining it to beginning
		}
	}

	// swapping two elements in a list
	private void swap(int first, int second, int quarter) {
		int temp = finalAverages.get(quarter).get(first); // setting first element to a temporary variable
		finalAverages.get(quarter).set(first, finalAverages.get(quarter).get(second)); // setting second element to first
		finalAverages.get(quarter).set(second, temp); // setting second element to temporary
	}

	// finding the maximum grade in a quarter starting from a certain index
	private int findMinFinal(int start, int quarter) {
		int min = finalAverages.get(quarter).get(start); // set maximum to first element
		int minIndex = start; // set maximum index to first index
		// looping through the array of numbers
		for (int i = start; i < finalAverages.get(quarter).size(); i++) {
			// if final average greater than max
			if (finalAverages.get(quarter).get(i) < min) {
				// set new maximum element and index
				min = finalAverages.get(quarter).get(i);
				minIndex = i;
			}
		}
		return minIndex;
	}

	// getting the frequency of each number into an array
	private ArrayList<Integer> getOccurance(int quarter) {
		ArrayList<Integer> occurance = new ArrayList<Integer>(); // frequency array
		// looping through the array of numbers
		for (int i = 0; i < finalAverages.get(quarter).size(); i++) {
			// add the count of each number into an array
			occurance.add(getCount(finalAverages.get(quarter).get(i), quarter));
		}
		return occurance;

	}

	// getting the count of any number in the array
	private int getCount(double number, int quarter) {
		int count = 0; // frequency of integer number
		// looping through the array of numbers
		for (int i = 0; i < finalAverages.get(quarter).size(); i++) {
			if (finalAverages.get(quarter).get(i) == number) { // if there is a match
				count++;
			}
		}
		return count;
	}

	// getting the standard deviation of the list of numbers
	public double getSD(int quarter) {
		if (finalAverages.get(quarter).size() == 1) {
			return 0;
		}
		double mean = getMean(quarter); // mean of numbers
		double sum = 0; // sum of all deviations
		// looping through the array of numbers
		for (int i = 0; i < finalAverages.get(quarter).size(); i++) {
			// adding up all squares of the deviations from the mean
			sum += Math.pow((finalAverages.get(quarter).get(i) - mean), 2);
		}
		// returns sum divided by n-1 and then square rooted
		return Math.sqrt((double) sum / (finalAverages.get(quarter).size() - 1));
	}

	// getting maximum number in an array of integers
	private int getMax(ArrayList<Integer> array) {
		int max = array.get(0); // initial maximum value in the list
		// looping through the array of numbers
		for (int i = 1; i < array.size(); i++) {
			// if number is greater than current maximum
			if (array.get(i) > max) {
				max = array.get(i); // set number as new maximum
			}
		}
		return max;
	}

	// getting the median of the list of numbers
	public double getMedian(int quarter) {
		sort(quarter);
		double median = 0;
		if (finalAverages.get(quarter).size() % 2 != 0) { // if there are an odd number of items in the list
			// median is the middle number
			median = finalAverages.get(quarter).get(finalAverages.get(quarter).size() / 2);
		} else { // if there are an even number of items in the list
			// median is average of two middle numbers
			median = (finalAverages.get(quarter).get(finalAverages.get(quarter).size() / 2)
					+ finalAverages.get(quarter).get(finalAverages.get(quarter).size() / 2 - 1)) / 2.0;
		}
		return median;
	}

}
