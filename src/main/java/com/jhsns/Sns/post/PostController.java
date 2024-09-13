package com.jhsns.Sns.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {
	@GetMapping("/list-view")
	public String list()
	{
		return "post/list";
	}
	
	@GetMapping("/create-view")
	public String create()
	{
		return "post/input";
	}
	
	@GetMapping("/timeline-view")
	public String timeLine()
	{
		return "post/timeline";
	}
}
