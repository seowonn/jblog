package jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.dto.CategoryWithPostCountDto;
import jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	private SqlSession sqlSession;
	
	public CategoryRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public void insert(CategoryVo categoryVo) {
		sqlSession.insert("category.insert", categoryVo);
	}

	public List<CategoryVo> getCategoriesByBlogId(String blogId) {
		return sqlSession.selectList("category.findByBlogId", blogId);
	}

	public List<CategoryWithPostCountDto> getCategories(String blogId) {
		return sqlSession.selectList("category.findCategoryWithBlogCountsByBlogId", blogId);
	}

	public void deleteById(int categoryId) {
		sqlSession.delete("category.deleteById", categoryId);
	}

}
