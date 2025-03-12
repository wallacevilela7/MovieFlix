package tech.wvs.movieflix.controller;

import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tech.wvs.movieflix.controller.request.CategoryRequest;
import tech.wvs.movieflix.controller.response.CategoryResponse;
import tech.wvs.movieflix.entity.Category;
import tech.wvs.movieflix.mapper.CategoryMapper;
import tech.wvs.movieflix.service.CategoryService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
        var createdCategory = categoryService.create(request);
        return ResponseEntity.created(URI.create("/movieflix/category/" + createdCategory.getId())).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAllCategories() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories
                .stream()
                .map(CategoryMapper::toResponse)
                .toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable("id") Long id) {
        var category = categoryService.findById(id);

        return category.isPresent() ?
                ResponseEntity.ok(CategoryMapper.toResponse(category.get())) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        var deleted = categoryService.deleteById(id);
        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
