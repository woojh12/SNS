package com.jhsns.Sns.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jhsns.Sns.common.FileManager;
import com.jhsns.Sns.dislike.service.DisLikeService;
import com.jhsns.Sns.like.service.LikeService;
import com.jhsns.Sns.post.domain.Comments;
import com.jhsns.Sns.post.domain.Post;
import com.jhsns.Sns.post.dto.CardView;
import com.jhsns.Sns.post.repository.PostRepository;
import com.jhsns.Sns.user.domain.User;
import com.jhsns.Sns.user.service.UserService;

@Service
public class PostService {
	private PostRepository postRepository;
	private UserService userService;
	private LikeService likeService;
	private DisLikeService disLikeService;
	
	@Autowired
	public PostService(PostRepository postRepository
			, UserService userService
			, LikeService likeService
			, DisLikeService disLikeService)
	{
		this.postRepository = postRepository;
		this.userService = userService;
		this.likeService = likeService;
		this.disLikeService = disLikeService;
	}
	
	public int addPost(int idKey, String userId, String title, MultipartFile file, String contents)
	{
		String imagePath = FileManager.saveFile(idKey, file);
		
		int count = postRepository.insertPost(idKey, userId, title, imagePath, contents);
		return count;
	}
	
	public int addComment(int postId, int userId, String userName, String comments)
	{
		return postRepository.insertComment(postId, userId, userName, comments);
	}
	
	// DTO 게시글 정보 가져오기 작성해야함 - 타임라인 기능
	public List<CardView> getPostList(int loginUserId)
	{
		List<Post> postList = postRepository.selectAllList();
		List<CardView> cardViewList = new ArrayList<>();
		
		// 각 게시글의 정보를 얻어오는 곳
		for(Post post:postList)
		{
			int userId = post.getIdKey();	// 게시글 사용자 PK
			User user = userService.getUserById(userId);
			
			int likeCount = likeService.getLikeCount(post.getId());
			
			boolean isLike = likeService.isLikeByUserIdAndPostId(loginUserId, post.getId());
			
			int disLikeCount = disLikeService.getDisLikeCount(post.getId());
			
			boolean disLike = disLikeService.isDisLikeByUserIdAndPostId(loginUserId, post.getId());
			
			CardView cardView = new CardView();
			cardView.setPostId(post.getId());
			cardView.setUserId(user.getId());
			cardView.setComments(post.getContents());
			cardView.setImagePath(post.getImagePath());
			cardView.setTitle(post.getTitle());
			cardView.setLikeCount(likeCount);
			cardView.setLike(isLike);
			cardView.setDisLikeCount(disLikeCount);
			cardView.setDisLike(disLike);
			
			cardViewList.add(cardView);
		}
		
		return cardViewList;
	}
	
	// 삭제 테스트 마무리 단계때
	/*
	public List<Post> getAllList()
	{
		return postRepository.selectAllList();
	}
	*/
	
	public List<Post> getUserList(int idKey)
	{
		return postRepository.selectList(idKey);
	}
	
	public Post getUser(int idKey, String title)
	{
		return postRepository.selectUser(idKey, title);
	}
	
	public int removePost(String title, String imagePath)
	{
		int count = 0;
		if(title != null)
		{
			FileManager.removeFile(imagePath);
			count = postRepository.deletePost(title);
		}
		return count;
	}
	
	public int removeComments(int postId)
	{
		return postRepository.deleteComments(postId);
	}
	
	public List<Comments> getAllCommentsList()
	{
		return postRepository.selectAllCommentsList();
	}
}
