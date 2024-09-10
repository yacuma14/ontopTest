package com.ontop.test.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Builder
@Data
public class UserWalletRequestDto {
	
	@NotNull
	private ClientWalletRequest clientWallet;
	
	@NotNull
	private DetailBankRequest detailBank;
	
	@NotNull
	private AmountTransferRequest amountTransferRequest;


}
