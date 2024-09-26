package com.jhsns.Sns.like.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhsns.Sns.like.domain.Like;
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
	
	public int deleteLike(int postId, int userId)
	{
		return likeRepository.deleteByPostIdAndUserId(postId, userId); 
	}
	
	public int getLikeCount(int postId)
	{
		return likeRepository.selectAllLike(postId);
	}
	
	// 특정 userId와 postId가 일치하는 행 조회
	// 특정사용자가 특정 게시글에 좋아요를 눌렀는지 확인
	public boolean isLikeByUserIdAndPostId(int userId, int postId)
	{
		int count = likeRepository.countByUserIdAndPostId(userId, postId);
		
		if(count == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public int removeLike(int postId)
	{
		return likeRepository.deleteLike(postId);
	}
}
