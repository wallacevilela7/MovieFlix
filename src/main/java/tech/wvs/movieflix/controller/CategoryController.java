package tech.wvs.movieflix.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.wvs.movieflix.controller.request.CategoryRequest;
import tech.wvs.movieflix.controller.response.CategoryResponse;
import tech.wvs.movieflix.entity.Category;
import tech.wvs.movieflix.mapper.CategoryMapper;
import tech.wvs.movieflix.service.CategoryService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
        var created = categoryService.create(request);
        return ResponseEntity.created(URI.create("/movieflix/category/" + created.getId())).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        List<Category> items = categoryService.findAll();
        return ResponseEntity.ok(items
                .stream()
                .map(CategoryMapper::toResponse)
                .toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable("id") Long id) {
        var entity = categoryService.findById(id);

        return entity.isPresent() ?
                ResponseEntity.ok(CategoryMapper.toResponse(entity.get())) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var deleted = categoryService.deleteById(id);
        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
