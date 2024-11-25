package com.exceptions;


public class InvalidPathExcelException extends FrameWorkException{
	public InvalidPathExcelException(String message){
		super(message);
	}
	public InvalidPathExcelException(String message ,Throwable cause){
		super(message,cause);
	}
}
