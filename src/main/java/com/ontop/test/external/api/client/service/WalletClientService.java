package com.ontop.test.external.api.client.service;

import com.ontop.test.external.api.request.WalletTransBalanceRequest;
import com.ontop.test.external.api.response.WalletBalanceResponse;

public interface WalletClientService {

	 public WalletBalanceResponse getBalanceByUserId(String clientID);
	 
	 public void updateBalanceUser(String clientID, WalletTransBalanceRequest walletTransBalanceRequest);
}
