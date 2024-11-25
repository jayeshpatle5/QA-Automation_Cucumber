package com.exceptions;

@SuppressWarnings("serial")
public class InputOutputException extends FrameWorkException {
	public InputOutputException(String message){
		super(message);
	}
	public InputOutputException(String message,Throwable cause){
		super(message,cause);
	}
}
