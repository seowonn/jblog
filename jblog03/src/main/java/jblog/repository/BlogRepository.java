package jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	
	private SqlSession sqlSession;
	
	public BlogRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public void addBlog(BlogVo blogVo) {
		sqlSession.insert("blog.insert", blogVo);
	}

	public BlogVo findById(String blogId) {
		return sqlSession.selectOne("blog.findById", blogId);
	}
	
	public void update(BlogVo blogVo) {
		sqlSession.update("blog.update", blogVo);
	}

}
