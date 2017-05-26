package ua.com.task.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.task.entity.Task;

public interface TaskDao extends JpaRepository<Task, Integer> {

	@Query("select t from Task t where t.customer.user.id=?1 ORDER BY t.date DESC")
	List<Task> findByUserId(int id);
	
	@Query("SELECT DISTINCT t FROM Task t LEFT JOIN FETCH t.performers WHERE "
			+ "t.id=:id")
	Task findPerformersWhoJoin(@Param("id") int id);
}
