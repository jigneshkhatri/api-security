/**
 * 
 */
package com.quallit.apisecurity.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JIGS
 *
 */
public class BusinessRuleValidationException extends QuallitException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4946089901583567144L;

	public BusinessRuleValidationException(String code) {
		super(code, MESSAGES.get(code));
	}

	public BusinessRuleValidationException(String code, String message) {
		super(code, message);
	}

	public static class Codes {
		private Codes() {
		}

		// QBRV - Quallit Business Rule Validation

		public static final String QBRV_001 = "QBRV_001";
		public static final String QBRV_002 = "QBRV_002";
		public static final String QBRV_003 = "QBRV_003";
	}

	private static final Map<String, String> MESSAGES = new HashMap<>();

	static {
		MESSAGES.put(Codes.QBRV_001, "Invalid Role");
		MESSAGES.put(Codes.QBRV_002, "Account with entered email already exists");
		MESSAGES.put(Codes.QBRV_003, "Account with entered mobile number already exists");
	}
}
