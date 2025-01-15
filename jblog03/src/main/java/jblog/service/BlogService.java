package jblog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.BlogRepository;
import jblog.repository.PostRepository;
import jblog.vo.BlogVo;
import jblog.vo.PostVo;

@Service
public class BlogService {
	
	private BlogRepository blogRepository;
	private PostRepository postRepository;
	
	public BlogService(BlogRepository blogRepository, PostRepository postRepository) {
		this.blogRepository = blogRepository;
		this.postRepository = postRepository;
	}

	public BlogVo getUserBlog(String blogId) {
		return blogRepository.findById(blogId);
	}
	
	public void updateBlog(BlogVo blogVo) {
		blogRepository.update(blogVo);
	}

	@Transactional
	public void addPost(PostVo postVo) {
		postRepository.addPost(postVo);
	}

}
