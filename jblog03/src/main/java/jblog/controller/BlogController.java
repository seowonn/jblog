package jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// 정규 표현을 써서 ~ 가능
@RequestMapping("/blog/{id:(?!assets).*}")
public class BlogController {

	// 같은 컨트롤러를 써야 하는 경로가 여러개일 때 {}를 사용한다.
	@RequestMapping({ "", "/{path1}", "/{path1}/{path2}" })
	public String index(@PathVariable("id") String id, @PathVariable("path1") Optional<Long> path1,
			@PathVariable("path2") Optional<Long> path2) {
		
		Long categoryId = 0L;
		Long postId = 0L;
		if(path2.isPresent()) {
			categoryId = path1.get();
			postId = path2.get();
		} else if(path1.isPresent()) {
			postId = path1.get();
		}
		
		// categoryId == 0L -> 기본 카테고리 id를 찾아서 대입해준다. (default categoryId)
		// postId == 0L -> default postId set 마찬가지
		
		System.out.println("BlogController.main(" + id + ", " + categoryId + ", " + postId);
		return "blog/main";
	}

//	@Auth // 애는 url의 이름을 떼와서 조회하고 role? 검사해서 거르기 
	@RequestMapping("/admin")
	public String adminDefault(@PathVariable("id") String id) {
		return "blog/main";
	}

}
