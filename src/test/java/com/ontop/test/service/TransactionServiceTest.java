package com.ontop.test.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ontop.test.entity.TransferEntity;
import com.ontop.test.exception.BalanceTransferAmountException;
import com.ontop.test.external.api.client.service.PaymentClientService;
import com.ontop.test.external.api.client.service.WalletClientService;
import com.ontop.test.external.api.response.WalletBalanceResponse;
import com.ontop.test.request.AmountTransferRequest;
import com.ontop.test.request.ClientWalletRequest;
import com.ontop.test.request.DetailBankRequest;
import com.ontop.test.request.UserWalletRequestDto;
import com.ontop.test.response.AmountTransferResponseDto;


@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
	
	
	@Mock
	private  WalletClientService walletClientService;
	
	@Mock
	private  PaymentClientService paymentClientService;
	
	@Mock
	private  TransferService transferService;
	
	@InjectMocks
	private TransactionServiceImpl transactionService;

	
	  @Test
      void whenExecuteTransferTransaction_returnSuccess() {
		  
		  WalletBalanceResponse walletBalanceResponse = WalletBalanceResponse.builder().
				  balance(100f).
				  username("yacuma").
				  build();
		  
		  
		  TransferEntity transferEntity = new TransferEntity();
		  transferEntity.setIdTransfer(12345l);
		  transferEntity.setAmount(10f);
		  transferEntity.setAccountClient("1234567");
		  

	    	UserWalletRequestDto userWalletRequestDto = UserWalletRequestDto.builder().
	    			clientWallet(
	    					ClientWalletRequest.builder().name("test").surname("yacuma").build()).
	    	        detailBank(
	    	        		DetailBankRequest.builder().accountNumber("12343454").bankName("bbv").nationalIdentificationNumber("1111").routingNumber("1233433").build()).
	    	        amountTransferRequest(
	    	        		AmountTransferRequest.builder().shippingAmount(10f).build()).
	    	build();
	    	
		  
          Mockito.when(walletClientService.getBalanceByUserId(Mockito.anyString())).thenReturn(walletBalanceResponse);
          Mockito.doNothing().when(walletClientService).updateBalanceUser(Mockito.anyString(), Mockito.any());
          Mockito.doNothing().when(paymentClientService).executeTransferUser(Mockito.any());
          Mockito.when(transferService.saveTransfer(Mockito.any())).thenReturn(transferEntity);
          
          AmountTransferResponseDto amountTransferResponseDto = transactionService.executeTransferTransaction(userWalletRequestDto);
          
          assertEquals(amountTransferResponseDto.getIdTransaction(), transferEntity.getIdTransfer());
          assertEquals(amountTransferResponseDto.getShippingAmount(), transferEntity.getAmount());

          
      }
	  
	  @Test
      void whenExecuteTransferTransaction_returnBalanceTransferAmountException() {

	    	UserWalletRequestDto userWalletRequestDto = UserWalletRequestDto.builder().
	    			clientWallet(
	    					ClientWalletRequest.builder().name("test").surname("yacuma").build()).
	    	        detailBank(
	    	        		DetailBankRequest.builder().accountNumber("12343454").bankName("bbv").nationalIdentificationNumber("1111").routingNumber("1233433").build()).
	    	        amountTransferRequest(
	    	        		AmountTransferRequest.builder().shippingAmount(10f).build()).
	    	build();
	    	
		  
          Mockito.when(walletClientService.getBalanceByUserId(Mockito.anyString())).thenReturn(null);
          assertThrows(BalanceTransferAmountException.class, () -> transactionService.executeTransferTransaction(userWalletRequestDto));
   }
	  
	  @Test
      void whenExecuteTransferTransaction_balanceHigherBalance_returnBalanceTransferAmountException() {
		  
		  WalletBalanceResponse walletBalanceResponse = WalletBalanceResponse.builder().
				  balance(5f).
				  username("yacuma").
				  build();

	    	UserWalletRequestDto userWalletRequestDto = UserWalletRequestDto.builder().
	    			clientWallet(
	    					ClientWalletRequest.builder().name("test").surname("yacuma").build()).
	    	        detailBank(
	    	        		DetailBankRequest.builder().accountNumber("12343454").bankName("bbv").nationalIdentificationNumber("1111").routingNumber("1233433").build()).
	    	        amountTransferRequest(
	    	        		AmountTransferRequest.builder().shippingAmount(10f).build()).
	    	build();
	    	
		  
          Mockito.when(walletClientService.getBalanceByUserId(Mockito.anyString())).thenReturn(walletBalanceResponse);
         
          assertThrows(BalanceTransferAmountException.class, () -> transactionService.executeTransferTransaction(userWalletRequestDto));
   }
	
}
