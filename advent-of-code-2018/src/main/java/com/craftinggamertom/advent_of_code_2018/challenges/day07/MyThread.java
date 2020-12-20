package com.craftinggamertom.advent_of_code_2018.challenges.day07;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.apache.log4j.Logger;

public class MyThread implements Runnable {

	private static Logger logger = Logger.getLogger(MyThread.class);
	private String command;

	/**
	 * constructor
	 *
	 * @param s command string
	 */
	public MyThread(String s) {
		this.command = s;
	}

	/**
	 * Checks if it should sleep or do work and does it
	 */
	public void run() {
		logger.info("Starting Thread: " + Thread.currentThread().getName() + " Start. Command = " + command);
		// Each thread gets its own connection

		while (!December07Part2.allLettersReached(PiecesAccessor.reached)) {
			logger.debug("Start Search Loop");
			for (char key : PiecesAccessor.inputs.keySet()) {
				// Ensure only one worker per letter
				if (PiecesAccessor.claimIfPossible(key)) {

					if (PiecesAccessor.reached.get(key) == false) {

						// logger.debug("Checking " + key);
						List<Character> prereqs = PiecesAccessor.inputs.get(key);
						boolean canReach = true;
						for (Character prereq : prereqs) {
							if (!PiecesAccessor.reached.get(prereq)) {
								// logger.debug(prereq + " yet not reached! Breaking loop");
								canReach = false;
								break;
							}
						}

						if (canReach) {
							logger.debug(key + " can be reached!");
							PiecesAccessor.reached.put(key, true);
							PiecesAccessor.answer = PiecesAccessor.answer + key;

							// Sleep
							try {
								Thread.sleep(600);
								Thread.sleep(PiecesAccessor.keyValuesMap.get(key) * 10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							// logger.debug("Currently reached: " + reached);
							break;
						}

					}
					// Remove claimed letter
					logger.debug("Removing: " + key);
					PiecesAccessor.inProgress.remove(PiecesAccessor.inProgress.indexOf(key));
					logger.debug("InProgress: " + PiecesAccessor.inProgress);
				}
			}
		}
		logger.info("Closing Thread: " + Thread.currentThread().getName());

		PiecesAccessor.finishTime = LocalDateTime.now();

		ZoneId zoneId = ZoneId.systemDefault(); // or: ZoneId.of("Europe/Oslo");
		long finishTimeEpoch = PiecesAccessor.finishTime.atZone(zoneId).toEpochSecond();

		long startTimeEpoch = PiecesAccessor.startTime.atZone(zoneId).toEpochSecond();

		logger.info("Answer: " + (finishTimeEpoch - startTimeEpoch));

	}

}