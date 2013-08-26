package com.imaginea.productapp.utilities;

import org.apache.log4j.Logger;

public class LoggerUtil
{
	
	private final Logger logger;
	
	public LoggerUtil(Logger logger) {
		super();
		this.logger = logger;
	}

	public enum Message
	{
		DEBUG, INFO, WARN, ERROR, FATAL,
	}

	public void log(Message severity, String message) {
		switch (severity) {
			case DEBUG:
				if (logger.isDebugEnabled()) {
					logger.debug(message);
				}
				break;
			case INFO:
				if (logger.isInfoEnabled()) {
					logger.info(message);
				}
				break;
			case WARN:
				logger.warn(message);
				break;
			case ERROR:
				logger.error(message);
				break;
			case FATAL:
				logger.fatal(message);
				break;
		}
	}

}
