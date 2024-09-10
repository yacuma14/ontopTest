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
public class ClientWalletRequest {

	@NotBlank(message = "Name is mandatory")
	private String name;

	@NotBlank(message = "Surname is mandatory")
	private String surname;
	
}
