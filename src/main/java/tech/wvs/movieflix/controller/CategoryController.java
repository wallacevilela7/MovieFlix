package tech.wvs.movieflix.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.wvs.movieflix.entity.Category;
import tech.wvs.movieflix.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping(path = "/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findAllCategories(){
        var categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }
}
