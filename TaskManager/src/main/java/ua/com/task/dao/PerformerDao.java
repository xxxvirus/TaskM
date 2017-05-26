package ua.com.task.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.task.entity.Performer;

public interface PerformerDao extends JpaRepository<Performer, Integer> {

	@Query("select p from Performer p join p.tasks t where t.id=?1")
	List<Performer> findByTaskId(int id);
}
