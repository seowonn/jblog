package jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	private SqlSession sqlSession;
	
	public CategoryRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public void addDefaultCategory(String blogId) {
		sqlSession.insert("category.insertDefaultCategory", blogId);
	}

	public List<CategoryVo> getCategoriesByBlogId(String blogId) {
		return sqlSession.selectList("category.findByBlogId", blogId);
	}

}
