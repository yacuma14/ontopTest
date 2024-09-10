package com.ontop.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontop.test.entity.TransferEntity;
import com.ontop.test.repository.TransferRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

	private final TransferRepository transferRepository;
	
	
	@Override
	@Transactional
	public TransferEntity saveTransfer(TransferEntity transferEntity) {
	    
		return transferRepository.save(transferEntity);
	}

	@Override
	public List<TransferEntity> getTransferByAccountClient(String accountClient) {
		
		return transferRepository.findByAccountClient(accountClient);
	}

}
