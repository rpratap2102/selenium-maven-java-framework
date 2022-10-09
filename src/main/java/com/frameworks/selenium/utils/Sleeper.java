package com.frameworks.selenium.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frameworks.selenium.exceptions.FrameworkException;

public final class Sleeper {

	private static final Logger log = LoggerFactory.getLogger(Sleeper.class);

	private Sleeper() {
	}

	private static void sleep(long ms, String reason) {
		final StringBuilder sb = new StringBuilder("Sleeping for ").append(ms).append("ms");

		if (reason != null) {
			sb.append(", because ").append(reason);
		}

		log.trace("{}", sb);
		try {
			Thread.sleep(ms);
		} catch (InterruptedException interruptedExp) {
			log.warn("Error while sleeping, reinterrupting", interruptedExp);
			throw new FrameworkException("Unable to sleep", interruptedExp);
		}
	}

	public static void sleep(int seconds, String reason) {
		sleep(seconds * 1000L, reason);
	}

}
