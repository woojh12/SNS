package com.jhsns.Sns.like.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeRepository {
	public int insertLike(@Param("postId") int postId
			, @Param("userId") int userId);
}
