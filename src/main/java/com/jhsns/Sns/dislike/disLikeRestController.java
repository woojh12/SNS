package com.jhsns.Sns.dislike;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhsns.Sns.dislike.service.DisLikeService;

import jakarta.servlet.http.HttpSession;

@RestController
public class disLikeRestController {
	private DisLikeService disLikeService;
	
	@Autowired
	public disLikeRestController(DisLikeService disLikeService)
	{
		this.disLikeService = disLikeService;
	}
	
	@PostMapping("/post/dislike")
	public Map<String, String> clickDisLike(@RequestParam("postId") int postId
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		int count = disLikeService.addDisLike(postId, userId);
		
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
