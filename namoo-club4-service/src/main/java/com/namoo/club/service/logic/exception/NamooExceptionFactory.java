package com.namoo.club.service.logic.exception;

public class NamooExceptionFactory {

	private NamooExceptionFactory() {
		//
	}
	
	public static NamooRuntimeException createRuntime(String msg) {
		//
		return new NamooRuntimeException(msg);
	}
}
