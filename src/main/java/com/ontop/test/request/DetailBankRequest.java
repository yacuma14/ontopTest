package com.ontop.test.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Data
@ToString
public class DetailBankRequest {

	@NotBlank(message = "Routing Number is mandatory")
	private String routingNumber;
	
	@NotBlank(message = "National Identification Number Number is mandatory")
	private String nationalIdentificationNumber;
	
	@NotBlank(message = "Account Number is mandatory")
	private String accountNumber;
	
	@NotBlank(message = "Bank Name is mandatory")
	private String bankName;
	
}
