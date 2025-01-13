package jblog.service;

import org.springframework.stereotype.Service;

import jblog.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public boolean getUser(String id) {
		return userRepository.findById(id);
	}

}
