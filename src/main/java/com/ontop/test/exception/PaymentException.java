package com.ontop.test.exception;

public class PaymentException  extends RuntimeException {

	  private static final long serialVersionUID = 1L;

	  public PaymentException(String msg) {
	    super(msg);
      }
}