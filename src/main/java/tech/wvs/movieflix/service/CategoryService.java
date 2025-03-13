package tech.wvs.movieflix.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.wvs.movieflix.controller.request.CategoryRequest;
import tech.wvs.movieflix.controller.response.CategoryResponse;
import tech.wvs.movieflix.controller.response.MovieResponse;
import tech.wvs.movieflix.entity.Category;
import tech.wvs.movieflix.mapper.CategoryMapper;
import tech.wvs.movieflix.mapper.MovieMapper;
import tech.wvs.movieflix.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(item -> CategoryMapper.toResponse(item))
                .toList();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category create(CategoryRequest request) {
        var category = CategoryMapper.toEntity(request);
        return categoryRepository.save(category);
    }

    public boolean deleteById(Long id) {
        var exists = categoryRepository.existsById(id);
        if (exists) {
            categoryRepository.deleteById(id);
        }

        return exists;
    }

    public Optional<Category> update(Long id, CategoryRequest request) {
        var entity = categoryRepository.findById(id);

        if (entity.isPresent()) {
            updateFields(request, entity);
            categoryRepository.save(entity.get());
        }

        return entity;
    }

    private void updateFields(CategoryRequest request, Optional<Category> entity) {
        entity.get().setName(request.name());
    }
}
