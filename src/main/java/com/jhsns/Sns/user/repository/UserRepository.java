package com.jhsns.Sns.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jhsns.Sns.user.domain.User;

@Mapper
public interface UserRepository {
	public int insertUser(
			@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("name") String name
			, @Param("phone") String phone
			, @Param("email") String email);
	
	public User selectLogin(
			@Param("loginId") String loginId
			, @Param("password") String password		
			);
}
