package jblog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jblog.dto.CategoryWithPostCountDto;
import jblog.repository.CategoryRepository;
import jblog.vo.CategoryVo;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Map<String, Object>> getCategories(String blogId) {
		List<CategoryVo> categoryList = categoryRepository.getCategoriesByBlogId(blogId);

		List<Map<String, Object>> result = new ArrayList<>();
		for (CategoryVo categoryVo : categoryList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", categoryVo.getId());
			map.put("name", categoryVo.getName());
			result.add(map);
		}
		return result;
	}

	public List<CategoryWithPostCountDto> getCategoriesWithBlogCnt(String blogId) {
		return categoryRepository.getCategories(blogId);
	}

	public void addCategory(CategoryVo categoryVo) {
		categoryRepository.insert(categoryVo);
	}

	public void deleteCategory(int categoryId) {
		categoryRepository.deleteById(categoryId);
	}

}
