package ua.com.task.service;

import ua.com.task.entity.User;

public interface UserService {

	void save(User user);

	User findByEmail(String email);
}
