package com.bank.accounts.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends AbstractEntity{

    @Column(name="account_number")
    @Id
    private Long accountNumber;

    private Long customerId;

    private String accountType;

    private String branchAddress;

}
