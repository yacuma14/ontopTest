package com.ontop.test.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TransactionResponseDto {

	private String concept;
    private String status;
	private Float amount;

}
