package com.ontop.test.external.api.response;

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
public class WalletBalanceResponse {
	 
	private String username;	
	private Float  balance;

	
	
	
}
