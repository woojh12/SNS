package com.jhsns.Sns.like.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhsns.Sns.like.repository.LikeRepository;

@Service
public class LikeService {
	private LikeRepository likeRepository;
	
	@Autowired
	public LikeService(LikeRepository likeRepository)
	{
		this.likeRepository = likeRepository;
	}
	
	public int addLike(int postId, int userId)
	{
		return likeRepository.insertLike(postId, userId);
	}
}
