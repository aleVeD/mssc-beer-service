package org.springframework.services;

import org.springframework.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
  CustomerDto getCustomerById(UUID id);

  CustomerDto saveNewCustomer(CustomerDto customerDto);


  void updateCustomer(UUID customerId, CustomerDto customerDto);
}
