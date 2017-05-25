package ua.com.task.service;

import java.util.List;

import ua.com.task.entity.Task;

public interface TaskService {

	void save(Task	task);
	List<Task> findAll();
	Task findOne(int id);
	void delete(int id);
	
	void createTask(Task task);
	void editTask(Task task);
	
}
