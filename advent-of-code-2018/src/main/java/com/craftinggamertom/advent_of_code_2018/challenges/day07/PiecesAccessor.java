package com.craftinggamertom.advent_of_code_2018.challenges.day07;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

public class PiecesAccessor {
	final static Logger logger = Logger.getLogger(PiecesAccessor.class);

	public static List<Character> preReqs;
	public static SortedMap<Character, Boolean> reached;
	public static String answer;
	public static SortedMap<Character, List<Character>> inputs;
	public static List<Character> inProgress;
	public static Map<Character, Long> keyValuesMap;
	public static int timeTaken;
	public static LocalDateTime startTime;
	public static LocalDateTime finishTime;

	public static synchronized boolean claimIfPossible(char key) {
		if (PiecesAccessor.inProgress.contains(key)) {
			// Already being worked on
			return false;
		} else {
			// Can be worked on
			logger.debug("Inserting: " + key);
			PiecesAccessor.inProgress.add(key);
			return true;
		}

	}

	public static void initKeyValMap() {
		keyValuesMap = new TreeMap<Character, Long>();
		keyValuesMap.put('A', 1L);
		keyValuesMap.put('B', 2L);
		keyValuesMap.put('C', 3L);
		keyValuesMap.put('D', 4L);
		keyValuesMap.put('E', 5L);
		keyValuesMap.put('F', 6L);
		keyValuesMap.put('G', 7L);
		keyValuesMap.put('H', 8L);
		keyValuesMap.put('I', 9L);
		keyValuesMap.put('J', 10L);
		keyValuesMap.put('K', 11L);
		keyValuesMap.put('L', 12L);
		keyValuesMap.put('M', 13L);
		keyValuesMap.put('N', 14L);
		keyValuesMap.put('O', 15L);
		keyValuesMap.put('P', 16L);
		keyValuesMap.put('Q', 17L);
		keyValuesMap.put('R', 18L);
		keyValuesMap.put('S', 19L);
		keyValuesMap.put('T', 20L);
		keyValuesMap.put('U', 21L);
		keyValuesMap.put('V', 22L);
		keyValuesMap.put('W', 23L);
		keyValuesMap.put('X', 24L);
		keyValuesMap.put('Y', 25L);
		keyValuesMap.put('Z', 26L);

	}
}
