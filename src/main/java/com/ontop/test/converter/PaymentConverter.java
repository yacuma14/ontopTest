package com.ontop.test.converter;

import com.ontop.test.external.api.request.PaymentTransferRequest;
import com.ontop.test.request.UserWalletRequestDto;

public final class PaymentConverter {

	PaymentConverter() {
	}


	public static PaymentTransferRequest toWalletTransBalanceRequestDto(final UserWalletRequestDto userWalletRequestDto) {
		
		
		return PaymentTransferRequest.builder()
				.accountOriginNumber(userWalletRequestDto.getDetailBank().getAccountNumber())
				.accountDestinyNumber(userWalletRequestDto.getDetailBank().getRoutingNumber())
				.amount(userWalletRequestDto.getAmountTransferRequest().getShippingAmount())
				.build();
		

  
	}

}