package tech.wvs.movieflix.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.wvs.movieflix.controller.request.CategoryRequest;
import tech.wvs.movieflix.entity.Category;
import tech.wvs.movieflix.mapper.CategoryMapper;
import tech.wvs.movieflix.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category create(CategoryRequest request) {
        var category = CategoryMapper.toEntity(request);
        return categoryRepository.save(category);
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public boolean deleteById(Long id) {
        var exists = categoryRepository.existsById(id);
        if (exists) {
            categoryRepository.deleteById(id);
        }

        return exists;
    }
}
