package ua.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ua.server.model.Category;
import ua.server.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController extends AbstractController<Category, Long> {

	private CategoryService service;

	@Autowired
	public CategoryController(CategoryService service) {
		super(service);
		this.service = service;
	}

	@RequestMapping(value = "findByName/{name}", method = RequestMethod.GET)
	public List<Category> findByName(@PathVariable String name) {
		return service.findByName(name);
	}
}
