package ua.com.task.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.task.entity.Report;

public interface ReportDao extends JpaRepository<Report, Integer> {

	@Query("select r from Report r where r.task.id=?1 ORDER BY r.date DESC")
	List<Report> findByTaskId(int id);
}
