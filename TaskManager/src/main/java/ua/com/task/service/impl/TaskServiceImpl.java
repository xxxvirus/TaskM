package ua.com.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ua.com.task.dao.CustomerDao;
import ua.com.task.dao.PerformerDao;
import ua.com.task.dao.TaskDao;
import ua.com.task.dao.UserDao;
import ua.com.task.entity.Customer;
import ua.com.task.entity.Performer;
import ua.com.task.entity.Task;
import ua.com.task.entity.User;
import ua.com.task.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private PerformerDao performerDao;
	@Autowired
	private UserDao userDao;

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
		int userId = user.getId();
		Customer customer = customerDao.findOne(userId);
		task.setCustomer(customer);
		taskDao.save(task);
	}

	@Override
	public void editTask(Task task) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Customer customer = customerDao.findOne(user.getId());
		task.setCustomer(customer);
		List<Performer> performers = performerDao.findByTaskId(task.getId());
		task.setPerformers(performers);
		taskDao.saveAndFlush(task);
	}

	@Override
	public List<Task> findByUserId(int id) {
		return taskDao.findByUserId(id);
	}

	@Override
	public void joinToTask(Task task, int id) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		task = taskDao.findOne(id);
		Performer performer = performerDao.findOne(user.getId());
		task = taskDao.findPerformersWhoJoin(id);
		task.getPerformers().add(performer);
		taskDao.save(task);
	}

	@Override
	public Task findPerformersWhoJoin(int id) {
		return taskDao.findPerformersWhoJoin(id);
	}

	@Override
	public void exitFromTeam(Task task, int id) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		task = taskDao.findOne(id);
		task = taskDao.findPerformersWhoJoin(id);
		task.getPerformers().removeIf(s -> s.getId() == user.getId());
		taskDao.save(task);
	}

	@Override
	public List<Task> findByUserIdactive(int id) {
		return taskDao.findByUserIdactive(id);
	}

	@Override
	public void finishTask(Task task, int id) {
		task = taskDao.findOne(id);
		task.setDone(true);
		taskDao.save(task);
	}

	@Override
	public List<Task> findAllActive() {
		return taskDao.findAllActive();
	}

	@Override
	public void openTask(Task task, int id) {
		task = taskDao.findOne(id);
		task.setDone(false);
		taskDao.save(task);
	}

	@Override
	public void shareTask(int idd, String email) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Customer customer = customerDao.findOne(user.getId());
		Task task = taskDao.findOne(idd);
		task.setCustomer(customer);
		User user2 = userDao.findByEmail(email);
		Performer performer = performerDao.findOne(user2.getId());
		List<Performer> performers = performerDao.findByTaskId(task.getId());
		task.setPerformers(performers);
		task.getPerformers().add(performer);
		taskDao.saveAndFlush(task);
	}

}
