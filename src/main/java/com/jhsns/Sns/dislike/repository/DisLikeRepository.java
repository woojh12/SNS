package com.jhsns.Sns.dislike.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DisLikeRepository {
	public int insertDisLike(@Param("postId") int postId
			, @Param("userId") int userId);
	
	public int selectAllDisLike(@Param("postId") int postId);
	
	public int countByUserIdAndPostId(@Param("userId") int userId, @Param("postId") int postId);
}
