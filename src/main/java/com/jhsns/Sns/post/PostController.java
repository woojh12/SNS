package com.jhsns.Sns.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhsns.Sns.post.domain.Post;
import com.jhsns.Sns.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {
	private PostService postService;
	
	public PostController(PostService postService)
	{
		this.postService = postService;
	}
	
	@GetMapping("/all-list-view")
	public String allList(Model model
			, HttpSession session)
	{	
		List<Post> allList = postService.getAllList();
		
		model.addAttribute("allList", allList);
		
		return "post/allList";
	}
	
	@GetMapping("/list-view")
	public String list(Model model
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		
		List<Post> userList = postService.getUserList(userId);
		
		model.addAttribute("userList", userList);
		
		return "post/list";
	}
	
	@GetMapping("/create-view")
	public String create()
	{
		return "post/input";
	}
	
	@GetMapping("/timeline-view")
	public String timeLine(@RequestParam("title") String title
			, @RequestParam("idKey") int idKey
			, Model model
			, HttpSession session)
	{
//		int userId = (Integer)session.getAttribute("userId");
		
		Post user = postService.getUser(idKey, title);
		
		model.addAttribute("user", user);
		
		return "post/timeline";
	}
}
