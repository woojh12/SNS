package com.jhsns.Sns.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jhsns.Sns.common.FileManager;
import com.jhsns.Sns.post.domain.Post;
import com.jhsns.Sns.post.repository.PostRepository;

@Service
public class PostService {
	private PostRepository postRepository;
	
	@Autowired
	public PostService(PostRepository postRepository)
	{
		this.postRepository = postRepository;
	}
	
	public int addPost(int idKey, String userId, String title, MultipartFile file, String contents)
	{
		String imagePath = FileManager.saveFile(idKey, file);
		
		int count = postRepository.insertPost(idKey, userId, title, imagePath, contents);
		return count;
	}
	
	public int addComment(int postId, int userId, String comments)
	{
		return postRepository.insertComment(postId, userId, comments);
	}
	
	public List<Post> getAllList()
	{
		return postRepository.selectAllList();
	}
	
	public List<Post> getUserList(int idKey)
	{
		return postRepository.selectList(idKey);
	}
	
	public Post getUser(int idKey, String title)
	{
		return postRepository.selectUser(idKey, title);
	}
	
	public int removePost(String title)
	{
		return postRepository.deletePost(title);
	}
}
