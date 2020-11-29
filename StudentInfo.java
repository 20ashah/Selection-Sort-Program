/*
 This class contains the attributes for a student object. It contains arrays
 for their tests, quizzes, and homework as well as name. This class 
 contains methods for determining test, quiz, and final averages. In addition, 
 two student objects can be compared in this method by either final average
 or by name.
*/

public class StudentInfo {

	// declare instance variables
	private int[][] testGrades; // holding all of the student's test grades for each quarter
	private int[][] quizGrades; // holding all of the student's quiz grades for each quarter
	private int[] homeworkAverages; // holding each homework average for all quarters
	private String name; // name of the student

	// constructor for setting student information
	public StudentInfo(int[][] tg, int[][] qg, int[] ha, String n) {
		// setting the student info to parameter information
		testGrades = tg;
		quizGrades = qg;
		homeworkAverages = ha;
		name = n;
	}

	// getting the name of the student
	public String getName() {
		return name;
	}

	// determining the final average for a particular quarter
	public int finalAverage(int quarter) {
		return (int) Math.round(
				0.5 * getTestAverage(quarter) + 0.3 * getQuizAverage(quarter) + 0.2 * getHomeworkAverage(quarter));
	}

	// getting the test average for a particular quarter
	private int getTestAverage(int quarter) {
		int sum = 0; // sum of all the student's test scores
		// looping through the test grades for that quarter
		for (int i = 0; i < testGrades[quarter].length; i++) {
			sum += testGrades[quarter][i];
		}
		// divide sum of test scores by number of test scores
		return (int) Math.round((double) sum / testGrades[quarter].length);
	}

	// getting the quiz average for a particular quarter
	private int getQuizAverage(int quarter) {
		int sum = 0; // sum of all the student's quiz scores
		// looping through the quiz grades for that quarter
		for (int i = 0; i < quizGrades[quarter].length; i++) {
			sum += quizGrades[quarter][i];
		}
		// divide sum of quiz scores by number of quiz scores
		return (int) Math.round((double) sum / quizGrades[quarter].length);
	}

	// getting the homework average for a particular quarter
	private int getHomeworkAverage(int quarter) {
		return homeworkAverages[quarter];
	}

	// comparing the names of two student objects
	public int compareName(StudentInfo s) {
		return name.compareTo(s.getName());
	}

	// comparing the final averages of two student objects
	public int compareFinalAverage(StudentInfo s, int quarter) {
		// if first student has a less final average than second student
		if (finalAverage(quarter) < s.finalAverage(quarter)) {
			return -1;
		// if first student has a higher final average than second student
		} else if (finalAverage(quarter) > s.finalAverage(quarter)) {
			return 1;
		} else { // if both students have the same final average
			return 0;
		}
	}

}
