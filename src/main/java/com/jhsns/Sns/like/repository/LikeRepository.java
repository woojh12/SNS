package com.jhsns.Sns.like.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jhsns.Sns.like.domain.Like;

@Mapper
public interface LikeRepository {
	public int insertLike(@Param("postId") int postId
			, @Param("userId") int userId);
	
	public int selectAllLike(@Param("postId") int postId);
	
	public int countByUserIdAndPostId(@Param("userId") int userId, @Param("postId") int postId);
	
	public int deleteByPostIdAndUserId(@Param("postId") int postId, @Param("userId") int userId);
	
	public int deleteLike(@Param("postId") int postId);
}
