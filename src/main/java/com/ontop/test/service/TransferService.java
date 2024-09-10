package com.ontop.test.service;

import java.util.List;

import com.ontop.test.entity.TransferEntity;

public interface TransferService {

	public TransferEntity saveTransfer(TransferEntity transferEntity );
	
	public List<TransferEntity> getTransferByAccountClient(String accountClient);
}
