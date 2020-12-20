package com.craftinggamertom.advent_of_code_2018.challenges.day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.craftinggamertom.advent_of_code_2018.challenges.day01.December01;
import com.craftinggamertom.advent_of_code_2018.tools.InputReader;

public class December04 {
	final static Logger logger = Logger.getLogger(December01.class);

	public static void main(String[] args) {
		try {
			logger.info("Answer: " + getAnswer());
		} catch (Exception e) {
			logger.error("Failed to run successfully!", e);
		}
	}

	private static String getAnswer() throws IOException {
		BufferedReader input = InputReader.getInputAsBufferedReader(2018, 4);

		int output = 0;
		// Tree will sort it
		SortedMap<String, String> tree = new TreeMap<String, String>();
		while (input.ready()) {
			// readLine removes the new-line characters
			String inputString = input.readLine();
			logger.debug("Input Line: " + inputString);
			if (inputString.length() > 18) {
				tree.put(inputString.substring(1, 17), inputString.substring(18));
			}
		}
		input.close();

		logger.debug("Tree: ");
		for (String key : tree.keySet()) {
			logger.debug(key + " : " + tree.get(key));
		}

		Map<String, int[]> guards = new TreeMap<String, int[]>();

		// Get all guard Ids
		for (String line : tree.values()) {
			if (line.contains("Guard")) {
				String[] parts = line.split(" ");
				String id = parts[2].substring(1);

				if (!guards.containsKey(id)) {
					guards.put(id, new int[60]);
				}
				logger.debug("New Guard Id: " + id);
			}
		}

		int sleep_time = 0;
		int wake_time = 0;
		String guard_id = "";
		int counter = 0;
		for (String time : tree.keySet()) {
			counter++;
			String event = tree.get(time);
			logger.debug("Time: " + time);
			logger.debug("Event: " + event);

			if (event.contains("Guard")) {
				String[] parts = event.split(" ");
				guard_id = parts[2].substring(1);
				logger.debug("Guard on duty: " + guard_id);
			}
			if (event.contains("asleep")) {
				sleep_time = Integer.parseInt(time.substring(14, 16));
				logger.debug("Sleeps: " + sleep_time);
			}
			if (event.contains("wakes")) {
				wake_time = Integer.parseInt(time.substring(14, 16));
				logger.debug("Wakes up: " + wake_time);
				int[] sleep_times = guards.get(guard_id);
				for (int index = sleep_time; index < wake_time; index++) {
					int new_value = (sleep_times[index] + 1);
					sleep_times[index] = new_value;
					logger.debug("SETTING INDEX: " + index + " TO: " + new_value);
				}
				logger.debug("Shift Info: " + guard_id + " slept: " + Arrays.toString(sleep_times));

			}
		}

		// Get longest sleeper & ideal break-in time

		String longest_sleeper_id = "";
		int longest_sleeper_minutes = 0;
		int longest_sleeper_ideal_time = -1;
		for (String id : guards.keySet()) {
			int[] sleep_minutes = guards.get(id);
			int total_sleep = 0;
			int most_times_slept_time = -1;
			int ideal_time = -1;

			for (int index = 0; index < 60; index++) {
				int times_slept_during_a_minute = sleep_minutes[index];
				total_sleep += times_slept_during_a_minute; // Set ideal break-in time
				if (times_slept_during_a_minute > most_times_slept_time) {
					most_times_slept_time = times_slept_during_a_minute;
					ideal_time = index;
				}
			}
			if (total_sleep > longest_sleeper_minutes) {
				longest_sleeper_id = id;
				longest_sleeper_minutes = total_sleep;
				longest_sleeper_ideal_time = ideal_time;
			}
		}

		partTwo(guards);
		/*
		 * // Get most best time to break in and guard at that time String
		 * longest_sleeper_id = ""; int longest_sleeper_ideal_time = -1; for (String id
		 * : guards.keySet()) { int[] sleep_minutes = guards.get(id); int total_sleep =
		 * 0; int most_times_slept_time = -1; int most_times_slept_at_time = 0;
		 * 
		 * for (int index = 0; index < 60; index++) { int times_slept_during_a_minute =
		 * sleep_minutes[index]; total_sleep += times_slept_during_a_minute; // Set
		 * ideal break-in time if (times_slept_during_a_minute >
		 * most_times_slept_at_time) { most_times_slept_time = index;
		 * most_times_slept_at_time = times_slept_during_a_minute; if
		 * (times_slept_during_a_minute > longest_sleeper_ideal_time) {
		 * longest_sleeper_id = id; longest_sleeper_ideal_time = most_times_slept_time;
		 * } } } }
		 */
		logger.info("Longest Sleeper Id: " + longest_sleeper_id);
		logger.info("Longest Sleeper Ideal Break-in Time: " + longest_sleeper_ideal_time);

		logger.info("Answer: " + (Integer.parseInt(longest_sleeper_id) * longest_sleeper_ideal_time));

		return null;
	}

	private static void partTwo(Map<String, int[]> guards) {
		String guardWhoSleptMost = "";
		int idealBreakInTime = -1;
		int timesSleptRepeatedly = 0;
		for (String id : guards.keySet()) {
			int[] sleep_minutes = guards.get(id);

			for (int index = 0; index < 60; index++) {
				int times_slept_during_a_minute = sleep_minutes[index];
				if (times_slept_during_a_minute > timesSleptRepeatedly) {
					idealBreakInTime = index;
					timesSleptRepeatedly = times_slept_during_a_minute;
					guardWhoSleptMost = id;
				}
			}
		}
		logger.info("Ideal Guard: " + guardWhoSleptMost + "Ideal Break-in Time: " + idealBreakInTime);
		logger.info("Part 2 Answer: " + (Integer.parseInt(guardWhoSleptMost) * idealBreakInTime));
	}

}
