package ua.com.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.task.dao.PerformerDao;
import ua.com.task.entity.Performer;
import ua.com.task.service.PerformerService;

@Service
public class PerformerServiceImpl implements PerformerService {

	@Autowired
	private PerformerDao performerDao;
	
	@Override
	public void save(Performer performer) {
		performerDao.save(performer);
	}

	@Override
	public List<Performer> findAll() {
		return performerDao.findAll();
	}

	@Override
	public Performer findOne(int id) {
		return performerDao.findOne(id);
	}

	@Override
	public void delete(int id) {
		performerDao.delete(id);
	}

}
