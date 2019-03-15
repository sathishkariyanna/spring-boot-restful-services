package com.sathish.springboot.restapi.exception;

import java.util.Date;

/**
 * 
 * @author Sathish
 *
 */
public class ExceptionResponseFormatedData {
	  private Date timestamp;
	  private String message;
	  private String details;

	  public ExceptionResponseFormatedData(Date timestamp, String message, String details) {
	    super();
	    this.timestamp = timestamp;
	    this.message = message;
	    this.details = details;
	  }

	  public Date getTimestamp() {
	    return timestamp;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public String getDetails() {
	    return details;
	  }

	}
