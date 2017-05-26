package ua.com.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ua.com.task.dao.PerformerDao;
import ua.com.task.dao.TaskDao;
import ua.com.task.entity.Performer;
import ua.com.task.entity.Task;
import ua.com.task.entity.User;
import ua.com.task.service.PerformerService;

@Service
public class PerformerServiceImpl implements PerformerService {

	@Autowired
	private PerformerDao performerDao;
	@Autowired
	private TaskDao taskDao;

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

	@Override
	public boolean isIamPerformer(int id) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Task task = taskDao.findPerformersWhoJoin(id);
		List<Performer> list = task.getPerformers();
		for (Performer performers : list) {
			if (performers.getId() == user.getId()) {
				return true;
			}
		}
		return false;
	}

	// @Override
	// public List<Performer> findByTaskId(int id) {
	// return performerDao.findByTaskId(id);
	// }

}
