/*
 * Name: Prof. Tony Silvestri
 * Date: 11/22/2020
 * Course Number: CSC-111
 * Course Name: Intro to Java Programming
 * Problem Number: State Data Processing V1
 * Email: silvestri@stcc.edu
 */

package statedatav1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import library.LinearSearch;

public class StateDataSearch {
	private final static String TITLE = "State Data Search App V1.0";
	private final static String CONTINUE_PROMPT = "Do this again? [y/N] ";
	private final static int STATECOUNT = 50;
	private final static String FILENAME = "statedata.txt";

	// **********************************************
	// Put as many methods you need here

	private static String getFirstCharacter(String str) {
		str = str.trim().toUpperCase();
		return str.isEmpty() ? "" : str.substring(0, 1);
	}

	private static void initializeDatabase(String[] stateNames, String[] stateCapitals, double[] stateArea,
			int[] stateYear, int[] stateOrder) throws FileNotFoundException {
		Scanner in = new Scanner(new File(FILENAME));
		for (int i = 0; i < STATECOUNT; i++) {
			String sn = in.nextLine();
			String sc = in.nextLine();
			double sa = in.nextDouble();
			int sy = in.nextInt();
			int so = in.nextInt();
			in.nextLine();

			stateNames[i] = sn;
			stateCapitals[i] = sc;
			stateArea[i] = sa;
			stateYear[i] = sy;
			stateOrder[i] = so;
		}
		in.close();
	}

	@SuppressWarnings("unused")
	private static void outputData(String[] stateNames, String[] stateCapitals) {
		for (int i = 0; i < stateNames.length; i++)
			System.out.printf("%15s%15s\n", stateNames[i], stateCapitals[i]);
		System.out.println();
	}

	private static void searchByStateName(Scanner sc, String[] stateNames, String[] stateCapitals) {
		System.out.println("Enter a State Name to get its Capital . . .");
		do {
			System.out.print("Enter State Name (EXIT to Quit): ");
			String name = sc.nextLine();
			if (name.equals("EXIT"))
				break;
			int index = LinearSearch.search(stateNames, name);
			if (index != -1)
				System.out.println(stateCapitals[index] + " is the capital of " + name);
			else
				System.out.println("No such state " + name);
		} while (true);
	}

	private static void searchByStateOrder(Scanner sc, String[] stateNames, int[] stateOrder) {
		System.out.println("Entering Searching By State Order Mode . . .");
		do {
			System.out.print("Enter State Order [1-50] (0 to Quit): ");
			int order = sc.nextInt();
			sc.nextLine(); // Consume Line
			if (order == 0)
				break;
			int index = LinearSearch.search(stateOrder, order);
			if (index != -1)
				System.out.println(stateNames[index] + " is state no. " + order);
			else
				System.out.println("No state matches order no. = " + order);
		} while (true);
	}

	// **********************************************
	// Start your logic coding in the process method
	private static void process(Scanner input, String args[]) throws Exception {
		// Parallel Arrays
		String stateNames[] = new String[STATECOUNT];
		String stateCapitals[] = new String[STATECOUNT];
		double stateArea[] = new double[STATECOUNT];
		int stateYear[] = new int[STATECOUNT];
		int stateOrder[] = new int[STATECOUNT];

		initializeDatabase(stateNames, stateCapitals, stateArea, stateYear, stateOrder);
		//outputData(stateNames, stateCapitals);

		String searchType;
		do {
			System.out.print("Enter Search Type Name to [C]apital, [O]rder to Name, E[X]it: ");
			searchType = getFirstCharacter(input.nextLine());
			switch (searchType) {
			case "C":
				searchByStateName(input, stateNames, stateCapitals);
				break;
			case "O":
				searchByStateOrder(input, stateNames, stateOrder);
				break;
			case "X":
				break;
			default:
				System.out.println("Bad Search Type Specified.");
				break;
			}
		} while (!searchType.equals("X"));
	}

	// **********************************************
	// Do not change the doThisAgain method
	private static boolean doThisAgain(Scanner input, String prompt) {
		System.out.print(prompt);
		String doOver = input.nextLine();
		return doOver.trim().equalsIgnoreCase("Y");
	}

	// **********************************************
	// Do not change the main method
	public static void main(String args[]) throws Exception {
		System.out.println("Welcome to " + TITLE);
		Scanner input = new Scanner(System.in);
		do {
			process(input, args);
		} while (doThisAgain(input, CONTINUE_PROMPT));
		input.close();
		System.out.println("Thank you for using " + TITLE);
	}

}
