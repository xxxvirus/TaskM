package ua.com.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.task.dao.ReportDao;
import ua.com.task.entity.Report;
import ua.com.task.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportDao reportDao;

	@Override
	public void save(Report report) {
		reportDao.save(report);
	}

	@Override
	public Report findOne(int id) {
		return reportDao.findOne(id);
	}

	@Override
	public void delete(int id) {
		reportDao.delete(id);
	}

	@Override
	public List<Report> findByTaskId(int id) {
		return reportDao.findByTaskId(id);
	}
}
