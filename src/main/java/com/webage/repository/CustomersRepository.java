package com.webage.repository;

import org.springframework.data.repository.CrudRepository;

import com.webage.domain.Customer;

public interface CustomersRepository extends CrudRepository<Customer, Long> {
	Customer findByName(String name);
}
