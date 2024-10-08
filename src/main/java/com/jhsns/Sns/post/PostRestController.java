package com.jhsns.Sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jhsns.Sns.dislike.service.DisLikeService;
import com.jhsns.Sns.like.service.LikeService;
import com.jhsns.Sns.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {
	private PostService postService;
	private LikeService likeService;
	private DisLikeService disLikeService;
	
	@Autowired
	public PostRestController(PostService postService
			, LikeService likeService
			, DisLikeService disLikeService)
	{
		this.postService = postService;
		this.likeService = likeService;
		this.disLikeService = disLikeService;
	}
	
	// 게시글 작성 API
	@PostMapping("/create")
	public Map<String, String> createPost(@RequestParam("title") String title
			, @RequestParam(value="imageFile", required=false) MultipartFile file
			, @RequestParam("contents") String contents
			, HttpSession session)
	{
		int idKey = (Integer)session.getAttribute("userId");
		String userId = (String)session.getAttribute("userName");
		
		int count = postService.addPost(idKey, userId, title, file, contents);
		
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
	
	// 댓글 API
	@PostMapping("/create-comment")
	public Map<String, String> createComment(@RequestParam("postId") int postId
			, @RequestParam("comments") String comments
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		int count = postService.addComment(postId, userId, userName, comments);
		
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
	
	// 게시글 삭제 
	@DeleteMapping("/delete")
	public Map<String, String> deletePost(@RequestParam("title") String title
			, @RequestParam(value="imagePath", required=false) String imagePath
			, @RequestParam("postId") int postId)
	{
		int count = postService.removePost(title,imagePath);
		int countComments = postService.removeComments(postId);
		int countLike = likeService.removeLike(postId);
		int countDisLike = disLikeService.removeDisLike(postId);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1 && countComments == 1)
		{
			resultMap.put("result", "success");
		}
		else if(count != 1 && countComments == 1)			
		{
			resultMap.put("result", "fail");
		}
		else if(count == 1 && countComments != 1)			
		{
			resultMap.put("result", "success");
		}
		else 		
		{
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
}
