package com.jhsns.Sns.like.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeRepository {
	public int insertLike(@Param("postId") int postId
			, @Param("userId") int userId);
	
	public int selectAllLike(@Param("postId") int postId);
	
	public int countByUserIdAndPostId(@Param("userId") int userId, @Param("postId") int postId);
}
