package jblog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.PostRepository;
import jblog.vo.PostVo;

@Service
public class PostService {

	private PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Transactional
	public void addPost(PostVo postVo) {
		postRepository.addPost(postVo);
	}

	private String formatRegDate(String regDate) {
		String[] info = regDate.split(" ");
		String[] date = info[0].split("-");
		return date[0] + "/" + date[1] + "/" + date[2];
	}

	public List<PostVo> getUserPost(String blogId, int categoryId) {
		List<PostVo> result = postRepository.findByBlogAndCategoryId(blogId, categoryId);

		List<PostVo> formattedResult = new ArrayList<>();
		for (PostVo postVo : result) {
			PostVo newPostVo = new PostVo();
			newPostVo.setCategoryId(postVo.getCategoryId());
			newPostVo.setContents(postVo.getContents());
			newPostVo.setPostId(postVo.getPostId());
			newPostVo.setTitle(postVo.getTitle());
			newPostVo.setRegDate(formatRegDate(postVo.getRegDate()));
			formattedResult.add(newPostVo);
		}
		return formattedResult;
	}

	public PostVo getSinglePost(String blogId, int categoryId, int postId) {
		return postRepository.findByBlogCategoryPostId(blogId, categoryId, postId);
	}

}
