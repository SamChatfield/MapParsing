package com.samchatfield.mapParsing;

public class MapFileException extends Exception {

	private static final long serialVersionUID = 9189277599293015515L;

	public MapFileException() {
		super();
	}

	public MapFileException(String message) {
		super(message);
	}

	public MapFileException(Throwable cause) {
		super(cause);
	}

	public MapFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public MapFileException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
