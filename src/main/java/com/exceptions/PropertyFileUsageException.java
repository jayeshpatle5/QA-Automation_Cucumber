package com.exceptions;


public class PropertyFileUsageException extends FrameWorkException{
	public PropertyFileUsageException(String message) {
		super (message);
	}
	public PropertyFileUsageException(String message,Throwable cause) {
		super (message,cause);
	}
}
