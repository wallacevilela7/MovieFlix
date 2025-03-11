package tech.wvs.movieflix.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.wvs.movieflix.entity.Category;
import tech.wvs.movieflix.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
