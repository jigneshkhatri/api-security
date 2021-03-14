/**
 * 
 */
package com.quallit.springbootstarter.exceptions;

/**
 * @author JIGS
 *
 */
public abstract class QuallitException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2034968792741451392L;

	private final String message;
	private final String code;

	protected QuallitException(String code, String message) {
		super(message);
		this.message = message;
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}
}
