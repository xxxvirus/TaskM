package ua.com.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.com.task.dao.CustomerDao;
import ua.com.task.dao.PerformerDao;
import ua.com.task.dao.UserDao;
import ua.com.task.entity.Customer;
import ua.com.task.entity.Performer;
import ua.com.task.entity.Role;
import ua.com.task.entity.User;
import ua.com.task.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private PerformerDao performerDao;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public void save(User user) {
		user.setRole(Role.ROLE_USER);
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.save(user);
		Customer customer = new Customer();
		customer.setUser(user);
		customerDao.save(customer);
		Performer performer = new Performer();
		performer.setUser(user);
		performerDao.save(performer);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return userDao.findByEmail(username);
	}

	@Override
	public User findOne(int id) {
		return userDao.findOne(id);
	}
	
//	@PostConstruct
//	public void addAdmin(){
//		User user = userDao.findByEmail("pavlo94@admin.ua");
//		if(user==null){
//			user = new User();
//			user.setName("Pavlo");
//			user.setSurname("Admin");
//			user.setCountry("Ukraine");
//			user.setCity("Lviv");
//			user.setEmail("pavlo94@admin.ua");
//			user.setPhoneNumber("0631234567");
//			user.setPassword(encoder.encode("admin"));
//			user.setRole(Role.ROLE_ADMIN);
//			userDao.save(user);
//		}
//	}

}
