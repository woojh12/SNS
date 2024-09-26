package com.jhsns.Sns.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jhsns.Sns.post.domain.Comments;
import com.jhsns.Sns.post.domain.Post;

@Mapper
public interface PostRepository {
	public int insertPost(@Param("idKey") int idKey
			, @Param("userId") String userId
			, @Param("title") String title
			, @Param("imagePath") String imagePath
			, @Param("contents") String contents);
	
	public int insertComment(@Param("postId") int postId
			, @Param("userId") int userId
			, @Param("userName") String userName
			, @Param("comments") String comments);
	
	public List<Post> selectAllList();
	
	public List<Post> selectList(@Param("idKey") int idKey);
	
	public Post selectUser(@Param("idKey") int idKey
			, @Param("title") String title);
	
	public int deletePost(@Param("title") String title);
	
	public int deleteComments(@Param("postId") int postId);
	
	public List<Comments> selectAllCommentsList();
}
