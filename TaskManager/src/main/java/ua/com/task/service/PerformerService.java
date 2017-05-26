package ua.com.task.service;

import java.util.List;

import ua.com.task.entity.Performer;

public interface PerformerService {

	void save(Performer performer);
	List<Performer> findAll();
	Performer findOne(int id);
	void delete(int id);
	
	boolean isIamPerformer(int id);
//	List<Performer> findByTaskId(int id);
}
