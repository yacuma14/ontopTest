package com.ontop.test.converter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.ontop.test.entity.TransferEntity;
import com.ontop.test.request.UserWalletRequestDto;
import com.ontop.test.response.TransactionResponseDto;

public final class TransferConverter {

	TransferConverter() {
	}


	public static TransferEntity toWalletRequestDto(final UserWalletRequestDto userWalletRequestDto, String concept, String status, float amount) {
		TransferEntity transferEntity = new TransferEntity();
		
		transferEntity.setAccountClient(userWalletRequestDto.getDetailBank().getAccountNumber());
		transferEntity.setConcept(concept); 
		transferEntity.setStatus(status);
		transferEntity.setAmount(amount);
		transferEntity.setCreatedDateTransaction(new Date());
		
		return transferEntity;
				

	}
	
	public static List<TransactionResponseDto> toTransferDto(final List<TransferEntity> transactions) {
        return transactions.stream().map(TransferConverter::toTransferEntityDto).collect(Collectors.toList());
    }
	
    public static TransactionResponseDto toTransferEntityDto(final TransferEntity transferEntity) {
	  return TransactionResponseDto.builder()
			  .amount(transferEntity.getAmount())
			  .concept(transferEntity.getConcept())
			  .status(transferEntity.getStatus())
			  .build();

	}
	
	

}