package com.jhsns.Sns.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jhsns.Sns.like.service.LikeService;
import com.jhsns.Sns.post.domain.Comments;
import com.jhsns.Sns.post.domain.Post;
import com.jhsns.Sns.post.dto.CardView;
import com.jhsns.Sns.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {
	private PostService postService;
	private LikeService likeService;
	
	public PostController(PostService postService, LikeService likeService)
	{
		this.postService = postService;
		this.likeService = likeService;
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
		// 댓글 조회 방식 -> 게시글을 추가하는 게시글 고유 번호와 댓글이 추가될때의 게시글 고유 번호 두개가 필요함.
		
		int loginId = (Integer)session.getAttribute("userId");
		
		List<CardView> cardViewList = postService.getPostList(loginId);
		
		List<Comments> commentsList = postService.getAllCommentsList();
		
		model.addAttribute("cardViewList", cardViewList);
		
		model.addAttribute("commentsList", commentsList);
		
		return "post/timeline";
	}
}
