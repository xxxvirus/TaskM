package ua.com.task.service;

import java.util.List;

import ua.com.task.entity.Report;

public interface ReportService {

	void save(Report report);
	Report findOne(int id);
	void delete(int id);
	
	List<Report> findByTaskId(int id);
}
