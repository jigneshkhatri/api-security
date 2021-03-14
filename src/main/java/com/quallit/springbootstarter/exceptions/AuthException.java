/**
 * 
 */
package com.quallit.springbootstarter.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JIGS
 *
 */
public class AuthException extends QuallitException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6007328785610704683L;

	public AuthException(String code) {
		super(code, MESSAGES.get(code));
	}

	public AuthException(String code, String message) {
		super(code, message);
	}

	public static class Codes {
		private Codes() {
		}

		// QA - Quallit Auth

		public static final String QA_001 = "QA_001";
		public static final String QA_002 = "QA_002";
		public static final String QA_003 = "QA_003";
		public static final String QA_004 = "QA_004";
		public static final String QA_005 = "QA_005";
		public static final String QA_006 = "QA_006";
		public static final String QA_007 = "QA_007";
		public static final String QA_008 = "QA_008";
	}

	private static final Map<String, String> MESSAGES = new HashMap<>();

	static {
		MESSAGES.put(Codes.QA_001, "Missing authentication token");
		MESSAGES.put(Codes.QA_002, "Invalid authentication token");
		MESSAGES.put(Codes.QA_003, "Authentication token is expired");
		MESSAGES.put(Codes.QA_004, "Unauthorized access to API");
		MESSAGES.put(Codes.QA_005, "Invalid username");
		MESSAGES.put(Codes.QA_006, "Invalid password");
		MESSAGES.put(Codes.QA_007, "Account is not active");
		MESSAGES.put(Codes.QA_008, "Unauthorized access to Data");
	}
}
