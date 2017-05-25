package ua.com.task.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.task.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

}
