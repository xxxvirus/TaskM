package ua.com.task.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.task.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{

	@Query("select u from User u where u.email=:param")
	User findByEmail(@Param("param") String email);
}
