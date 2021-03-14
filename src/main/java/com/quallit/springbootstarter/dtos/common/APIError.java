/**
 * 
 */
package com.quallit.springbootstarter.dtos.common;

import java.util.Date;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author JIGS
 *
 */
public class APIError {

	/** The message. */
	private String message;

	/** The timestamp. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;

	/** The status. */
	private HttpStatus status;

	/** The debug message. */
	private String debugMessage;

	/** The error code. */
	private String errorCode;

	private Map<String, String> fieldErrors;

	/**
	 * Instantiates a new API error.
	 *
	 * @param apiErrorBuilder the api error builder
	 */
	private APIError(APIErrorBuilder apiErrorBuilder) {
		this.debugMessage = apiErrorBuilder.debugMessage;
		this.errorCode = apiErrorBuilder.errorCode;
		this.message = apiErrorBuilder.message;
		this.status = apiErrorBuilder.status;
		this.timestamp = apiErrorBuilder.timestamp;
		this.fieldErrors = apiErrorBuilder.fieldErrors;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * Gets the debug message.
	 *
	 * @return the debug message
	 */
	public String getDebugMessage() {
		return debugMessage;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Gets the field errors.
	 *
	 * @return the field errors
	 */
	public Map<String, String> getFieldErrors() {
		return fieldErrors;
	}

	/**
	 * The Class APIErrorBuilder.
	 */
	public static class APIErrorBuilder {

		/** The message. */
		private String message;

		/** The status. */
		private HttpStatus status;

		/** The debug message. */
		private String debugMessage;

		/** The error code. */
		private String errorCode;

		/** The timestamp. */
		private Date timestamp;

		/** The field errors. */
		private Map<String, String> fieldErrors;

		/**
		 * Instantiates a new API error.
		 */
		public APIErrorBuilder() {
			this.timestamp = new Date();
		}

		/**
		 * Instantiates a new API error.
		 *
		 * @param status  the status
		 * @param message the message
		 */
		public APIErrorBuilder(HttpStatus status, String message) {
			this();
			this.status = status;
			this.message = message;
		}

		/**
		 * Message.
		 *
		 * @param message the message
		 * @return the API error builder
		 */
		public APIErrorBuilder message(String message) {
			this.message = message;
			return this;
		}

		/**
		 * Status.
		 *
		 * @param status the status
		 * @return the API error builder
		 */
		public APIErrorBuilder status(HttpStatus status) {
			this.status = status;
			return this;
		}

		/**
		 * Debug message.
		 *
		 * @param debugMessage the debug message
		 * @return the API error builder
		 */
		public APIErrorBuilder debugMessage(String debugMessage) {
			this.debugMessage = debugMessage;
			return this;
		}

		/**
		 * Error code.
		 *
		 * @param errorCode the error code
		 * @return the API error builder
		 */
		public APIErrorBuilder errorCode(String errorCode) {
			this.errorCode = errorCode;
			return this;
		}

		/**
		 * Field errors.
		 *
		 * @param fieldErrors the field errors
		 * @return the API error builder
		 */
		public APIErrorBuilder fieldErrors(Map<String, String> fieldErrors) {
			this.fieldErrors = fieldErrors;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the API error
		 */
		public APIError build() {
			return new APIError(this);
		}

	}
}
