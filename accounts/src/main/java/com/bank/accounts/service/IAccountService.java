package com.bank.accounts.service;


import com.bank.accounts.dto.CustomerDto;

public interface IAccountService {


    void createAccount(CustomerDto customerDto);

    CustomerDto getAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);

}
