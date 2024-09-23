package com.jhsns.Sns.dislike.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhsns.Sns.dislike.repository.DisLikeRepository;

@Service
public class DisLikeService {
	private DisLikeRepository disLikeRepository;
	
	@Autowired
	public DisLikeService(DisLikeRepository disLikeRepository)
	{
		this.disLikeRepository = disLikeRepository;
	}
	
	public int addDisLike(int postId, int userId)
	{
		return disLikeRepository.insertDisLike(postId, userId);
	}
	
	public int getDisLikeCount(int postId)
	{
		return disLikeRepository.selectAllDisLike(postId);
	}
	
	public boolean isDisLikeByUserIdAndPostId(int userId, int postId)
	{
		int count = disLikeRepository.countByUserIdAndPostId(userId, postId);
		
		if(count == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
