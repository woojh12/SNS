package com.jhsns.Sns.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhsns.Sns.post.domain.Post;
import com.jhsns.Sns.post.dto.CardView;
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
	
	/* 삭제 테스트 마무리 단계때
	@GetMapping("/all-list-view")
	public String allList(Model model
			, HttpSession session)
	{	
		List<Post> allList = postService.getAllList();
		
		model.addAttribute("allList", allList);
		
		return "post/allList";
	}
	*/
	
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
	public String timeLine(Model model
			, HttpSession session)
	{	
		List<CardView> cardViewList = postService.getPostList();
		
		model.addAttribute("cardViewList", cardViewList);
		
		return "post/timeline";
	}
}
