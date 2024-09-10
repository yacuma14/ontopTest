package com.ontop.test.external.api.client.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontop.test.exception.WalletBalanceException;
import com.ontop.test.external.api.request.WalletTransBalanceRequest;
import com.ontop.test.external.api.response.WalletBalanceResponse;
import com.ontop.test.util.UtilConstant;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class WalletClientServiceImpl implements WalletClientService  {

   private final RestClient restClient;

   
   @Value("${api.wallet.host}")
   private String walletUriHost;
   
   public WalletClientServiceImpl() {
       restClient = RestClient.builder()
               .build();
   }

   @Override
   public WalletBalanceResponse getBalanceByUserId(String clientID){
	    var urlParams = new HashMap<String, Object>();
        urlParams.put(UtilConstant.WALLET_CLIENT_ID, clientID);
        

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(walletUriHost+"/api/v1/wallet/{clientId}/balance");

        String resultBalance = restClient.get()
 			   .uri(builder.buildAndExpand(urlParams).toUri().toString())
 			   .accept(MediaType.APPLICATION_JSON)
 			   .retrieve()
 			   .onStatus(status -> status.value() != 200, (request, response) -> {
 			       throw new WalletBalanceException("Error calling wallet:"+response.getStatusCode());
 			   })
 			   .body(String.class);
        
        log.info("Result api client getBalanceByUserId:"+resultBalance);
	   
        return  parsingWalletBalanceResponse(resultBalance);     
   }

   
   @Override
   public void updateBalanceUser(String clientID, WalletTransBalanceRequest walletTransBalanceRequest) {
	    var urlParams = new HashMap<String, Object>();
        urlParams.put(UtilConstant.WALLET_CLIENT_ID, clientID);
        

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(walletUriHost+"/api/v1/wallet/{clientId}/balance");
        restClient
        .put()
        .uri(builder.buildAndExpand(urlParams).toUri().toString())
	    .contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
        .body(walletTransBalanceRequest)
        .retrieve()
		   .onStatus(status -> status.value() != 201, (request, response) -> {
		       throw new WalletBalanceException("Error updating wallet:"+response.getStatusCode());
		   });
   }
   
   private WalletBalanceResponse parsingWalletBalanceResponse(String body) {
    ObjectMapper map = new ObjectMapper();
	
    try {
		WalletBalanceResponse walletBalanceResponse = map.readValue(body, WalletBalanceResponse.class);
		return walletBalanceResponse;

	} catch (JsonProcessingException e) {
		throw new WalletBalanceException("Error parsing response:"+body);
	}
   }
	
}
