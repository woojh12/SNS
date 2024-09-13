package com.jhsns.Sns.post;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/post")
@RestController
public class PostRestController {
	
	@PostMapping("/create")
	public Map<String, String> createPost()
	{
		return null;
	}
}
