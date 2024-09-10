package com.ontop.test.entity;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "transfer")
public class TransferEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Transfer", unique = true, nullable = false)
	private Long idTransfer;

    @Column(name = "concept")
	private String concept;

    @Column(name = "account_client")
	private String accountClient;

    @Column(name = "status")
	private String status;

    @Column(name = "amount")
	private Float  amount;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date_transaction")
	private Date createdDateTransaction;

}
