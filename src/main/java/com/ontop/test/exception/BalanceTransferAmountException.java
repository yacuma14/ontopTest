package com.ontop.test.exception;

public class BalanceTransferAmountException  extends RuntimeException {

	  private static final long serialVersionUID = 1L;

	  public BalanceTransferAmountException(String msg) {
	    super(msg);
      }
}