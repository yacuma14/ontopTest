package com.ontop.test.service;

import java.util.List;

import com.ontop.test.request.UserWalletRequestDto;
import com.ontop.test.response.AmountTransferResponseDto;
import com.ontop.test.response.TransactionResponseDto;

public interface TransactionService {

	public AmountTransferResponseDto executeTransferTransaction(UserWalletRequestDto userWalletRequestDto);

	public List<TransactionResponseDto> getTransactionsByAccountClient(String accountClient);
}
