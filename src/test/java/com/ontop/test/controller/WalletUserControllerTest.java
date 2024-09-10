package com.ontop.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontop.test.main.OntopApplication;
import com.ontop.test.request.AmountTransferRequest;
import com.ontop.test.request.ClientWalletRequest;
import com.ontop.test.request.DetailBankRequest;
import com.ontop.test.request.UserWalletRequestDto;
import com.ontop.test.response.AmountTransferResponseDto;
import com.ontop.test.service.TransactionServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@ContextConfiguration(classes = OntopApplication.class)
public class WalletUserControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private TransactionServiceImpl userPagoService;
	
	

    @Autowired
    private ObjectMapper objectMapper;
    
    
    @Test
    public void givenWalletObject_whenWalletTransaction_thenReturnSavedTransaction() throws Exception{

    	AmountTransferResponseDto pagoResponseDto = AmountTransferResponseDto.builder().build();
    	
    	UserWalletRequestDto userWalletRequestDto = UserWalletRequestDto.builder().
    			clientWallet(
    					ClientWalletRequest.builder().name("test").surname("yacuma").build()).
    	        detailBank(
    	        		DetailBankRequest.builder().accountNumber("12343454").bankName("bbv").nationalIdentificationNumber("1111").routingNumber("1233433").build()).
    	        amountTransferRequest(
    	        		AmountTransferRequest.builder().shippingAmount(10f).build()).
    	build();
    	
    	Mockito.when(userPagoService.executeTransferTransaction(Mockito.any())).thenReturn(pagoResponseDto);
    	  

        ResultActions response = mockMvc.perform(post("/api/v1/transaction/transfer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userWalletRequestDto))
            .accept(MediaType.APPLICATION_JSON_VALUE));
            

        response.andDo(print()).
                andExpect(status().isCreated());

        }
    
    @Test
    public void givenWalletObject_whenWalletTransaction_thenReturnError() throws Exception{

    	
    	UserWalletRequestDto userWalletRequestDto = UserWalletRequestDto.builder().
    			clientWallet(
    					ClientWalletRequest.builder().name("test").surname("yacuma").build()).
    	        detailBank(
    	        		DetailBankRequest.builder().accountNumber("12343454").bankName("bbv").nationalIdentificationNumber("1111").routingNumber("1233433").build()).
    	        amountTransferRequest(
    	        		AmountTransferRequest.builder().shippingAmount(10f).build()).
    	build();
    	  
    Mockito.doCallRealMethod().when(userPagoService).executeTransferTransaction(userWalletRequestDto);

        ResultActions response = mockMvc.perform(post("/api/v1/transaction/transfer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userWalletRequestDto))
            .accept(MediaType.APPLICATION_JSON_VALUE));
            

        response.andDo(print()).
                andExpect(status().is5xxServerError());

        }
    
    @Test
    public void givenWalletObject_whenWalletTransaction_thenReturnBadRequest() throws Exception{

    	AmountTransferResponseDto pagoResponseDto = AmountTransferResponseDto.builder().build();
    	
    	UserWalletRequestDto userWalletRequestDto = UserWalletRequestDto.builder().
    			detailBank(
    	        		DetailBankRequest.builder().accountNumber("12343454").bankName("bbv").nationalIdentificationNumber("1111").routingNumber("1233433").build()).
    	        amountTransferRequest(
    	        		AmountTransferRequest.builder().shippingAmount(10f).build()).
    	build();
    	
    	Mockito.when(userPagoService.executeTransferTransaction(Mockito.any())).thenReturn(pagoResponseDto);
    	  

        ResultActions response = mockMvc.perform(post("/api/v1/transaction/transfer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userWalletRequestDto))
            .accept(MediaType.APPLICATION_JSON_VALUE));
            

        response.andDo(print()).
                andExpect(status().is4xxClientError());

        }

}
