package com.ontop.test.external.api.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class WalletTransBalanceRequest {

	 private String accountOriginNumber;
	 private Float  amount;
	 
}
