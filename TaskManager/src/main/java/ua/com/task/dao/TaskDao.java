package ua.com.task.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.task.entity.Task;

public interface TaskDao extends JpaRepository<Task, Integer> {

}
