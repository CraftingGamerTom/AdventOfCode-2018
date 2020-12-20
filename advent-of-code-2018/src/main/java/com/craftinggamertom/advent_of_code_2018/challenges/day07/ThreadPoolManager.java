package com.craftinggamertom.advent_of_code_2018.challenges.day07;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager {
	private static final int THREAD_COUNT = 5;
	private ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

	/**
	 * constructor
	 */
	public ThreadPoolManager() {
	}

	/**
	 * start thread pools
	 */
	public void startThreads() {
		PiecesAccessor.inProgress = new ArrayList<Character>(); // init inProgress tracker
		PiecesAccessor.initKeyValMap();
		PiecesAccessor.timeTaken = 0;
		
		PiecesAccessor.startTime = LocalDateTime.now();

		for (int i = 1; i <= THREAD_COUNT; i++) {
			Runnable wt = new MyThread(String.format("MyThread%d", i));
			executor.execute(wt);
		}
	}

	/**
	 * shutdown thread pools
	 */
	public void stopThreads() {
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
	}
}
