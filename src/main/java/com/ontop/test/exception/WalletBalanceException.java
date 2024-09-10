package com.ontop.test.exception;

public class WalletBalanceException  extends RuntimeException {

	  private static final long serialVersionUID = 1L;

	  public WalletBalanceException(String msg) {
	    super(msg);
      }
}