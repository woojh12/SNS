package com.jhsns.Sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhsns.Sns.user.domain.User;
import com.jhsns.Sns.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserRestController {
	private UserService userService;
	
	public UserRestController(UserService userService)
	{
		this.userService = userService;
	}
	
	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("phone") String phone
			, @RequestParam("email") String email)
	{
		int count = userService.addUser(loginId, password, name, phone, email);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1)
		{
			resultMap.put("result", "success");
		}
		else
		{
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpServletRequest request)
	{
		User user = userService.loginUser(loginId, password);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(user != null)
		{
			resultMap.put("result", "success");
			
			HttpSession session = request.getSession();
			
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
		}
		else
		{
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
}
