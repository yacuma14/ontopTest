package com.ontop.test.converter;

import com.ontop.test.external.api.request.WalletTransBalanceRequest;
import com.ontop.test.request.UserWalletRequestDto;


public final class WalletConverter {

	WalletConverter() {
	}

	public static WalletTransBalanceRequest toWalletTransBalanceRequestDto(final UserWalletRequestDto userWalletRequestDto) {
		
		
		return WalletTransBalanceRequest.builder()
				.accountOriginNumber(userWalletRequestDto.getDetailBank().getAccountNumber())
				.amount(userWalletRequestDto.getAmountTransferRequest().getShippingAmount())
				.build();
		

  
	}

}