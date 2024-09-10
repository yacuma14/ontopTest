package com.ontop.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ontop.test.bean.EnumPaymentStatus;
import com.ontop.test.converter.PaymentConverter;
import com.ontop.test.converter.TransferConverter;
import com.ontop.test.converter.WalletConverter;
import com.ontop.test.entity.TransferEntity;
import com.ontop.test.exception.BalanceTransferAmountException;
import com.ontop.test.external.api.client.service.PaymentClientService;
import com.ontop.test.external.api.client.service.WalletClientService;
import com.ontop.test.external.api.response.WalletBalanceResponse;
import com.ontop.test.request.UserWalletRequestDto;
import com.ontop.test.response.AmountTransferResponseDto;
import com.ontop.test.response.TransactionResponseDto;
import com.ontop.test.util.Util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    
	private final WalletClientService walletClientService;
	
	private final PaymentClientService paymentClientService;
	
	private final TransferService transferService;
	
	private final static String TRANSACTION_CONCEPT="Transaccion con el banco %s  fue %s";
	
	@Value("${transaction_fee}")
	private float TRANSACTION_FEE;
	
	private String WALLET_USER_MOCK = "123456"; 
	
	@Override
	public AmountTransferResponseDto executeTransferTransaction(UserWalletRequestDto userWalletRequestDto) {
		log.info("executeTransferTransaction::"+userWalletRequestDto);
		
		Float feeAmount = (userWalletRequestDto.getAmountTransferRequest().getShippingAmount() + Util.calculateFeeAmount(
				userWalletRequestDto.getAmountTransferRequest().getShippingAmount(), TRANSACTION_FEE));
		
		WalletBalanceResponse walletBalanceResponse = walletClientService.getBalanceByUserId(WALLET_USER_MOCK);
 		
		if(!ObjectUtils.isEmpty(walletBalanceResponse)) {
 			if( walletBalanceResponse.getBalance() <= feeAmount ) { 
				throw new  BalanceTransferAmountException("balance menor a la transferencia");
			}    			
		}else {
			throw new  BalanceTransferAmountException("no existe balance con el cliente");
		}  
		
		walletClientService.updateBalanceUser(WALLET_USER_MOCK, WalletConverter.toWalletTransBalanceRequestDto(userWalletRequestDto));
		
		paymentClientService.executeTransferUser(PaymentConverter.toWalletTransBalanceRequestDto(userWalletRequestDto));
		
		TransferEntity saveTransfer = 
				transferService.saveTransfer(TransferConverter.toWalletRequestDto(userWalletRequestDto,  
						String.format(TRANSACTION_CONCEPT,userWalletRequestDto.getDetailBank().getBankName(), 
						  EnumPaymentStatus.COMPLETADO.name()), 
						  EnumPaymentStatus.COMPLETADO.name() , feeAmount));
		
		
		return AmountTransferResponseDto.builder()
				.fee(TRANSACTION_FEE)
				.idTransaction(saveTransfer.getIdTransfer())
				.shippingAmount(userWalletRequestDto.getAmountTransferRequest().getShippingAmount())
				.shippingAmountWithFee(saveTransfer.getAmount())
				.build() ;
	}

	@Override
	public List<TransactionResponseDto> getTransactionsByAccountClient(String accountClient) {
		return  TransferConverter.toTransferDto( transferService.getTransferByAccountClient(accountClient));
	}
	


}
