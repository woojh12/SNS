package com.jhsns.Sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhsns.Sns.like.service.LikeService;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {
	private LikeService likeService;
	
	@Autowired
	public LikeRestController(LikeService likeService)
	{
		this.likeService = likeService;
	}
	
	public Map<String, String> clickLike(@RequestParam("postId") int postId
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		int count = likeService.addLike(postId, userId);
		
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
}
