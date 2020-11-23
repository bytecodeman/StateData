/*
 * Name: Prof. Tony Silvestri
 * Date: 11/22/2020
 * Course Number: CSC-111
 * Course Name: Intro to Java Programming
 * Problem Number: State Data Processing V4
 * Email: silvestri@stcc.edu
 */

package statedatav4;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import library.LinearSearch;

public class StateDataSearch {
	private final static String TITLE = "State Data Search App V4.0";
	private final static String CONTINUE_PROMPT = "Do this again? [y/N] ";
	private static final String DATABASEFILE = "statedata.txt";
	private static final int STATECOUNT = 50;

	// **********************************************
	// Put as many methods you need here

	private static String getFirstCharacter(String str) {
		str = str.trim().toUpperCase();
		return str.isEmpty() ? "" : str.substring(0, 1);
	}

	private static void initializeDatabase(StateData[] states) throws IOException  {
		URL url = new URL("https://cs.stcc.edu/~silvestri/csc111/" + DATABASEFILE);
		Scanner in = new Scanner(url.openStream());
		for (int i = 0; i < STATECOUNT; i++) {
			String sn = in.nextLine();
			String sc = in.nextLine();
			double sa = in.nextDouble();
			int sy = in.nextInt();
			int so = in.nextInt();
			in.nextLine();
			
			states[i] = new StateData(sn, sc, sa, sy, so);
		}
		in.close();
	}

	private static void outputData(StateData[] states) {
		for (int i = 0; i < states.length; i++)
			System.out.println(states[i]);
		System.out.println();
	}

	private static void searchByStateName(Scanner sc, StateData[] states) {
		System.out.println("Enter a State Name to get its Capital . . .");
		do {
			System.out.print("Enter State Name (EXIT to Quit): ");
			String name = sc.nextLine();
			if (name.equals("EXIT"))
				break;
			
			String stateNames[] = new String[STATECOUNT];
			for (int i = 0; i < STATECOUNT; i++) {
				stateNames[i] = states[i].name;
			}

			int index = LinearSearch.search(stateNames, name);
			if (index != -1)
				System.out.println(states[index].capital + " is the capital of " + name);
			else
				System.out.println("No such state " + name);
		} while (true);
	}

	private static void searchByStateOrder(Scanner sc, StateData[] states) {
		System.out.println("Entering Searching By State Order Mode . . .");
		do {
			System.out.print("Enter State Order [1-50] (0 to Quit): ");
			int order = sc.nextInt();
			sc.nextLine(); // Consume Line
			if (order == 0)
				break;
			
			int stateOrders[] = new int[STATECOUNT];
			for (int i = 0; i < STATECOUNT; i++) {
				stateOrders[i] = states[i].order;
			}

			int index = LinearSearch.search(stateOrders, order);
			if (index != -1)
				System.out.println(states[index].name + " is state no. " + order);
			else
				System.out.println("No state matches order no. = " + order);
		} while (true);
	}

	// **********************************************
	// Start your logic coding in the process method
	private static void process(Scanner input, String args[]) throws Exception {
		StateData states[] = new StateData[STATECOUNT];

		initializeDatabase(states);
		outputData(states);

		String searchType;
		do {
			System.out.print("Enter Search Type Name to [C]apital, [O]rder to Name, E[X]it: ");
			searchType = getFirstCharacter(input.nextLine());
			switch (searchType) {
			case "C":
				searchByStateName(input, states);
				break;
			case "O":
				searchByStateOrder(input, states);
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
