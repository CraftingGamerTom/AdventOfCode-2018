package com.craftinggamertom.advent_of_code_2018.challenges.day07;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.craftinggamertom.advent_of_code_2018.challenges.day01.December01;
import com.craftinggamertom.advent_of_code_2018.tools.InputReader;

public class December07 {
	final static Logger logger = Logger.getLogger(December07.class);

	public static void main(String[] args) {
		try {
			logger.info("Answer: " + getAnswer());
		} catch (Exception e) {
			logger.error("Failed to run successfully!", e);
		}
	}

	private static String getAnswer() throws IOException {
		logger.debug("Entered getAnswer()");
		BufferedReader input = InputReader.getInputAsBufferedReader(2018, 7);

		String answer = "";

		SortedMap<Character, List<Character>> inputs = new TreeMap<Character, List<Character>>();
		initMap(inputs);

		// Create list of pre-requisites
		logger.debug("Creating PreReqs list");
		String inputString;
		while (input.ready()) {
			// readLine removes the new-line characters
			inputString = input.readLine();
			String[] parts = inputString.split(" ");

			Character newprereq = parts[1].charAt(0);
			char letter = parts[7].charAt(0);

			logger.debug(newprereq + " must be reached before " + letter);

			List<Character> preReqs = inputs.get(letter);
			logger.debug(inputs.toString());
			logger.debug(letter + "'s PreReqs are: " + preReqs.toString());
			preReqs.add(newprereq);
			logger.debug(letter + "'s PreReqs are: " + preReqs.toString());

			inputs.put(letter, preReqs);

		}
		input.close();

		// Now solve
		SortedMap<Character, Boolean> reached = new TreeMap<Character, Boolean>();
		initReached(reached);

		while (!allLettersReached(reached)) {
			logger.debug("Start Search Loop");
			for (char key : inputs.keySet()) {
				if (reached.get(key) == false) {

					// logger.debug("Checking " + key);
					List<Character> prereqs = inputs.get(key);
					boolean canReach = true;
					for (Character prereq : prereqs) {
						if (!reached.get(prereq)) {
							// logger.debug(prereq + " yet not reached! Breaking loop");
							canReach = false;
							break;
						}
					}

					if (canReach) {
						logger.debug(key + " can be reached!");
						reached.put(key, true);
						answer = answer + key;

						// logger.debug("Currently reached: " + reached);
						break;
					}

				}
			}

		}

		logger.debug("Reached: " + reached);
		return answer;

	}

	private static void initReached(SortedMap<Character, Boolean> reached) {
		reached.put('A', false);
		reached.put('B', false);
		reached.put('C', false);
		reached.put('D', false);
		reached.put('E', false);
		reached.put('F', false);
		reached.put('G', false);
		reached.put('H', false);
		reached.put('I', false);
		reached.put('J', false);
		reached.put('K', false);
		reached.put('L', false);
		reached.put('M', false);
		reached.put('N', false);
		reached.put('O', false);
		reached.put('P', false);
		reached.put('Q', false);
		reached.put('R', false);
		reached.put('S', false);
		reached.put('T', false);
		reached.put('U', false);
		reached.put('V', false);
		reached.put('W', false);
		reached.put('X', false);
		reached.put('Y', false);
		reached.put('Z', false);
	}

	private static boolean allLettersReached(SortedMap<Character, Boolean> reached) {
		for (Boolean isReached : reached.values()) {
			if (!isReached) {
				return false;
			}
		}
		return true;
	}

	private static void initMap(SortedMap<Character, List<Character>> inputs) {
		inputs.put('A', new ArrayList<Character>());
		inputs.put('B', new ArrayList<Character>());
		inputs.put('C', new ArrayList<Character>());
		inputs.put('D', new ArrayList<Character>());
		inputs.put('E', new ArrayList<Character>());
		inputs.put('F', new ArrayList<Character>());
		inputs.put('G', new ArrayList<Character>());
		inputs.put('H', new ArrayList<Character>());
		inputs.put('I', new ArrayList<Character>());
		inputs.put('J', new ArrayList<Character>());
		inputs.put('K', new ArrayList<Character>());
		inputs.put('L', new ArrayList<Character>());
		inputs.put('M', new ArrayList<Character>());
		inputs.put('N', new ArrayList<Character>());
		inputs.put('O', new ArrayList<Character>());
		inputs.put('P', new ArrayList<Character>());
		inputs.put('Q', new ArrayList<Character>());
		inputs.put('R', new ArrayList<Character>());
		inputs.put('S', new ArrayList<Character>());
		inputs.put('T', new ArrayList<Character>());
		inputs.put('U', new ArrayList<Character>());
		inputs.put('V', new ArrayList<Character>());
		inputs.put('W', new ArrayList<Character>());
		inputs.put('X', new ArrayList<Character>());
		inputs.put('Y', new ArrayList<Character>());
		inputs.put('Z', new ArrayList<Character>());
	}

}
