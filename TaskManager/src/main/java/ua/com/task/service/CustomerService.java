package ua.com.task.service;

import java.util.List;

import ua.com.task.entity.Customer;

public interface CustomerService {

	void save(Customer customer);
	List<Customer> findAll();
	Customer findOne(int id);
	void delete(int id);
}
