package ua.com.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.task.dao.CustomerDao;
import ua.com.task.entity.Customer;
import ua.com.task.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public Customer findOne(int id) {
		return customerDao.findOne(id);
	}

	@Override
	public void delete(int id) {
		customerDao.delete(id);
	}

}
