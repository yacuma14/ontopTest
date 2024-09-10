package com.ontop.test.external.api.client.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.ontop.test.exception.PaymentException;
import com.ontop.test.external.api.request.PaymentTransferRequest;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class PaymentClientServiceImpl implements PaymentClientService {

   private final RestClient restClient;

   
   @Value("${api.payment.host}")
   private String walletUriHost;
   
   public PaymentClientServiceImpl() {
       restClient = RestClient.builder()
               .build();
   }

   @Override
   public void executeTransferUser(PaymentTransferRequest paymentTransferRequest) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(walletUriHost+"/api/v1/payment");
        restClient
        .post()
        .uri(builder.buildAndExpand().toUri().toString())
	    .contentType(MediaType.APPLICATION_JSON)
	    .body(paymentTransferRequest)
        .retrieve()
		   .onStatus(status -> status.value() != 201, (request, response) -> {
		       throw new PaymentException("Error executing transfer:"+response.getStatusCode());
		   });
   }
   
}
