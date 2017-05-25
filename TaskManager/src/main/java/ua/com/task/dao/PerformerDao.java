package ua.com.task.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.task.entity.Performer;

public interface PerformerDao extends JpaRepository<Performer, Integer>{

}
