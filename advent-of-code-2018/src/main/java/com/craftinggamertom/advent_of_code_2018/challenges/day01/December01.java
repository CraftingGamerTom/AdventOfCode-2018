package com.craftinggamertom.advent_of_code_2018.challenges.day01;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.craftinggamertom.advent_of_code_2018.tools.InputReader;

public class December01 {

	final static Logger logger = Logger.getLogger(December01.class);

	public static void main(String[] args) {
		try {
			logger.info("Answer: " + getAnswer());
		} catch (Exception e) {
			logger.error("Failed to run successfully!", e);
		}
	}

	private static String getAnswer() throws IOException {
		BufferedReader input = InputReader.getInputAsBufferedReader(2018, 1);

		int output = 0;
		String inputString;
		while (input.ready()) {
			// readLine removes the new-line characters
			inputString = input.readLine();

			// Get operator
			char operator = inputString.charAt(0);

			// Do math
			switch (operator) {
			case '+':
				output = output + Integer.parseInt(inputString.substring(1));
				break;
			case '-':
				output = output - Integer.parseInt(inputString.substring(1));
				break;
			default:
				throw new IllegalArgumentException("Bad operator symbol!");
			}

		}
		input.close();
		return String.valueOf(output);
	}

}
