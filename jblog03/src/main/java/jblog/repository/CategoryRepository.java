package jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {
	
	private SqlSession sqlSession;
	
	public CategoryRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public void addDefaultCategory(String blogId) {
		sqlSession.insert("category.insertDefaultCategory", blogId);
	}

}
