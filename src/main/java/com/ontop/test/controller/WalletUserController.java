package com.ontop.test.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ontop.test.request.UserWalletRequestDto;
import com.ontop.test.response.AmountTransferResponseDto;
import com.ontop.test.response.TransactionResponseDto;
import com.ontop.test.service.TransactionService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "ontop", description = "Test ontop APIs")
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class WalletUserController {

	@Autowired
	private TransactionService transactionService;

	@Operation(
		      summary = "execute balance transaction",
		      description = "execute balance transaction")
	
	@ApiResponses({
		      @ApiResponse(responseCode = "201", description = "created"),
		      @ApiResponse(responseCode = "400", description = "badRequest"),
		      @ApiResponse(responseCode = "500", description = "Internal Error")})

	@PostMapping("/transfer")
	public ResponseEntity<AmountTransferResponseDto> transferUser(@Valid 
			@RequestBody UserWalletRequestDto userWalletRequestDto) {
		log.info("UserWalletRequestDto request {}",userWalletRequestDto);	
		return new ResponseEntity<AmountTransferResponseDto>(
	    		 transactionService.executeTransferTransaction(userWalletRequestDto) , HttpStatus.CREATED);
	
	}
	
	@Operation(
		      summary = "Retrieve a transactions by account",
		      description = "Retrieve a transactions by account.")
	
	@ApiResponses({
		      @ApiResponse(responseCode = "200", description = "success"),
		      @ApiResponse(responseCode = "400", description = "badRequest"),
		      @ApiResponse(responseCode = "500", description = "internal error")})
	
	@GetMapping("/{accountClient}/get")
	public ResponseEntity<List<TransactionResponseDto>> getTransactionsByAccountClient(
			@PathVariable("accountClient") String accountClient) {
		return new ResponseEntity<List<TransactionResponseDto>>(
				transactionService.getTransactionsByAccountClient(accountClient), HttpStatus.OK);
	}

}
