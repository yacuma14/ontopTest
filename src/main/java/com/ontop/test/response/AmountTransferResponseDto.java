package com.ontop.test.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class AmountTransferResponseDto {

	private Long idTransaction;
	private Float shippingAmount;
	private Float fee;
	private Float shippingAmountWithFee;
	
	
}
