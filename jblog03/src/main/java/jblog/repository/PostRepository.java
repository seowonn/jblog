package jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.PostVo;

@Repository
public class PostRepository {

	private SqlSession sqlSession;

	public PostRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public void addPost(PostVo postVo) {
		sqlSession.insert("post.insert", postVo);
	}

	public void deletePostByCategoryId(int categoryId) {
		sqlSession.delete("post.deleteByCategoryId", categoryId);
	}

	public List<PostVo> findByBlogAndCategoryId(String blogId, int categoryId) {
		return sqlSession.selectList("post.findByBlogId",
				Map.of("blogId", blogId, "categoryId", categoryId));
	}

	public PostVo findByBlogCategoryPostId(String blogId, int categoryId,
			int postId) {
		return sqlSession.selectOne("post.findByBlogCategoryPostId",
				Map.of("blogId", blogId, "categoryId", categoryId, "postId", postId));
	}

}
