package com.bank.accounts.accounts;


import com.bank.accounts.dto.CustomerDto;
import com.bank.accounts.entity.Accounts;
import com.bank.accounts.entity.Customer;
import com.bank.accounts.repository.AccountRepository;
import com.bank.accounts.repository.CustomerRepository;
import com.bank.accounts.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class AccountServiceTest {

    @Mock
    private CustomerRepository customerRepo;

    @Mock
    private AccountRepository accountRepo;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreateAccount_whenCustomerDoesNotExist() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Emre Durmus");
        customerDto.setEmail("emredurmus@hotmail.com");
        customerDto.setMobileNumber("4141418343");

        when(customerRepo.findByMobileNumber("4141418343")).thenReturn(Optional.empty());
        when(customerRepo.save(any(Customer.class))).thenAnswer(invocation -> {
            Customer savedCustomer = invocation.getArgument(0);
            savedCustomer.setCustomerId(1L);
            return savedCustomer;
        });

        when(accountRepo.save(any(Accounts.class))).thenAnswer(invocation -> {
            Accounts savedAccount = invocation.getArgument(0);
            savedAccount.setAccountNumber(1234567890L);
            return savedAccount;
        });

        accountService.createAccount(customerDto);


        verify(customerRepo, times(1)).save(any(Customer.class));
        System.out.println("customerRepo.save çağrıldı!");

        verify(accountRepo, times(1)).save(any(Accounts.class));
        System.out.println("accountRepo.save çağrıldı!");

        verify(accountRepo, times(1)).save(argThat(account -> account.getCustomerId().equals(1L)));
        System.out.println("Accounts nesnesi doğru customerId ile kaydedildi!");
    }


}
