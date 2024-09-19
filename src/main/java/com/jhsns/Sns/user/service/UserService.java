package com.jhsns.Sns.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhsns.Sns.common.MD5HashingEncoder;
import com.jhsns.Sns.user.domain.User;
import com.jhsns.Sns.user.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	public int addUser(String loginId
			, String password
			, String name
			, String phone
			, String email)
	{
		String encryptPassword = MD5HashingEncoder.encode(password);
		
		int count = userRepository.insertUser(loginId, encryptPassword, name, phone, email);
		
		return count;
	}
	
	public User loginUser(String loginId
			, String password)
	{
		String encryptPassword = MD5HashingEncoder.encode(password);
		
		return userRepository.selectLogin(loginId, encryptPassword);
	}
	
	public User getUserById(int id)
	{
		return userRepository.selectUserById(id);
	}
}
