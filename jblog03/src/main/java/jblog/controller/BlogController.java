package jblog.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jblog.dto.CategoryWithPostCountDto;
import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.FileUploadService;
import jblog.service.PostService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Controller
@RequestMapping("/jblog/{id:(?!assets).*}") // 정규식 사용
public class BlogController {

	private final BlogService blogService;
	private final FileUploadService fileUploadService;
	private final ServletContext servletContext;
	private final CategoryService categoryService;
	private final PostService postService;

	public BlogController(BlogService blogService, FileUploadService fileUploadService,
			ServletContext servletContext, CategoryService categoryService,
			PostService postService) {
		this.blogService = blogService;
		this.fileUploadService = fileUploadService;
		this.servletContext = servletContext;
		this.categoryService = categoryService;
		this.postService = postService;
	}

	// 같은 컨트롤러를 써야 하는 경로가 여러개일 때 {}를 사용한다.
	@RequestMapping({ "", "/{path1}", "/{path1}/{path2}" })
	public String index(@PathVariable("id") String blogId,
			@PathVariable("path1") Optional<Integer> path1,
			@PathVariable("path2") Optional<Integer> path2, Model model) {

		int categoryId = 1, postId = 0;
		if (path2.isPresent()) {
			categoryId = path1.get();
			postId = path2.get();
		} else if (path1.isPresent()) {
			categoryId = path1.get();
		}

		if ("main".equals(blogId)) {
			return "blog/main";
		}

		BlogVo blogVo = blogService.getUserBlog(blogId);
		model.addAttribute("blogVo", blogVo);

		List<PostVo> postList = postService.getUserPost(blogId, categoryId);
		PostVo selectedPost = postService.getSinglePost(blogId, categoryId, postId);
		model.addAttribute("postVo", postList);
		model.addAttribute("post", selectedPost);

		List<CategoryWithPostCountDto> list = categoryService
				.getCategoriesWithBlogCnt(blogId);
		model.addAttribute("data", list);

		return "blog/main";
	}

	@RequestMapping("/admin/basic")
	public String adminDefault(@PathVariable("id") String blogId, Model model) {
		model.addAttribute("menu", "basic");
		return "blog/admin-basic";
	}

	@RequestMapping("/admin/update")
	public String update(@PathVariable("id") String blogId, BlogVo blogVo,
			@RequestParam("file") MultipartFile multipartFile) {
		String profile = fileUploadService.restore(multipartFile);
		if (profile != null) {
			blogVo.setProfile(profile);
		}

		blogService.updateBlog(blogVo);
		servletContext.setAttribute("blogVo", blogVo);

		return "redirect:/jblog/" + blogVo.getBlogId();
	}

	@RequestMapping("/admin/write")
	public String getWriteForm(@PathVariable("id") String blogId, Model model) {
		model.addAttribute("menu", "write");
		List<Map<String, Object>> categories = categoryService.getCategories(blogId);
		model.addAttribute("category", categories);
		return "blog/admin-write";
	}

	@PostMapping("/admin/write")
	public String addPost(@PathVariable("id") String blogId, PostVo postVo, Model model) {
		postService.addPost(postVo);
		return "redirect:/jblog/" + blogId;
	}

	@RequestMapping("/admin/category")
	public String getCategoryPage(@PathVariable("id") String blogId, Model model) {
		model.addAttribute("menu", "category");
		List<CategoryWithPostCountDto> list = categoryService
				.getCategoriesWithBlogCnt(blogId);
		model.addAttribute("data", list);
		return "blog/admin-category";
	}

	@PostMapping("/admin/category/add")
	public String addCategory(@PathVariable("id") String blogId, CategoryVo categoryVo) {
		categoryVo.setBlogId(blogId);
		categoryService.addCategory(categoryVo);

		// 브라우저에게 새로운 GET 요청을 보내도록 redirect 사용
		return "redirect:/jblog/" + blogId + "/admin/category";
	}

	@RequestMapping("/admin/category/delete")
	public String deleteCategory(@PathVariable("id") String blogId,
			@RequestParam("category-id") int categoryId) {
		categoryService.deleteCategory(categoryId);
		return "redirect:/jblog/" + blogId + "/admin/category";
	}

}
