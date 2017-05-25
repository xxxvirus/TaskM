package ua.com.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ua.com.task.dao.CustomerDao;
import ua.com.task.dao.TaskDao;
import ua.com.task.entity.Customer;
import ua.com.task.entity.Task;
import ua.com.task.entity.User;
import ua.com.task.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public void save(Task task) {
		taskDao.save(task);
	}

	@Override
	public List<Task> findAll() {
		return taskDao.findAll();
	}

	@Override
	public Task findOne(int id) {
		return taskDao.findOne(id);
	}

	@Override
	public void delete(int id) {
		taskDao.delete(id);
	}

	@Override
	public void createTask(Task task) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Customer customer = new Customer();
		customer.setUser(user);
		customerDao.save(customer);
		task.setCustomer(customer);
		taskDao.save(task);
	}

	@Override
	public void editTask(Task task) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Customer customer = new Customer();
		customer.setUser(user);
		customerDao.save(customer);
		task.setCustomer(customer);
		taskDao.saveAndFlush(task);
	}

}
