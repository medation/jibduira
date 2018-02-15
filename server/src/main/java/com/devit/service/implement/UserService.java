package com.devit.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devit.model.User;
import com.devit.repository.UserRepository;
import com.devit.service.IUserService;

@Service("userService")
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public User findByUsernameOrEmail(String usernameOrEmail) {
		try {
			User user = userRepository.findByUsernameOrEmail(usernameOrEmail);
			return user;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(int id){
		User user = userRepository.findOne(id);
		return user;
	}

	@Override
	public boolean UserNameOrEmailExists(String usernameOrEmail) {
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail);
		if(user == null) return false;
		else return true;
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

}
