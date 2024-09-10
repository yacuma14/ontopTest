create table transfer (
        id_transfer bigint not null auto_increment,
        account_client varchar(255),
        amount float(23),
        concept varchar(255),
        created_date_transaction date,
        status varchar(255),
        primary key (id_transfer)
    ) engine=InnoDB