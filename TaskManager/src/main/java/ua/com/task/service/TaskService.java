package ua.com.task.service;

import java.util.List;

import ua.com.task.entity.Task;

public interface TaskService {

	void save(Task	task);
	List<Task> findAll();
	Task findOne(int id);
	void delete(int id);
	
	List<Task> findAllActive();
	
	void createTask(Task task);
	void editTask(Task task);
	void shareTask(int idd, String email);
	
	List<Task> findByUserId(int id);
	List<Task> findByUserIdactive(int id);
	
	void joinToTask(Task task, int id);
	void exitFromTeam(Task task, int id);
	void finishTask(Task task, int id);
	void openTask(Task task, int id);
	Task findPerformersWhoJoin(int id);
	
}
