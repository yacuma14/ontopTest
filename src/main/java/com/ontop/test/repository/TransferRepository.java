package com.ontop.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ontop.test.entity.TransferEntity;

public interface TransferRepository extends JpaRepository<TransferEntity, Long>   {

	public List<TransferEntity> findByAccountClient(String accountClient);
}
