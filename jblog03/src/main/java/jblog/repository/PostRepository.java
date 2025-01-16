package jblog.repository;

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
	
}
