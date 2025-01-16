package jblog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.BlogRepository;
import jblog.repository.CategoryRepository;
import jblog.repository.UserRepository;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.UserVo;

@Service
public class UserService {

	private UserRepository userRepository;
	private BlogRepository blogRepository;
	private CategoryRepository categoryRepository;

	public UserService(UserRepository userRepository, BlogRepository blogRepository,
			CategoryRepository categoryRepository) {
		this.userRepository = userRepository;
		this.blogRepository = blogRepository;
		this.categoryRepository = categoryRepository;
	}

	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}
	
	public UserVo getUser(String email, String password) {
		return userRepository.findByIdAndPassword(email, password);
	}

	@Transactional
	public void join(UserVo userVo) {
		userRepository.addUser(userVo);
		
		BlogVo blogVo = new BlogVo();
		blogVo.setBlogId(userVo.getId());
		blogRepository.addBlog(blogVo);
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(userVo.getId());
		categoryVo.setDescription("기본 카테고리");
		categoryVo.setName("기본 카테고리");
		categoryRepository.insert(categoryVo);
	}

}
