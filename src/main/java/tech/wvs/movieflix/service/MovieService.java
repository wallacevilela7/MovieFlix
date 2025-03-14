package tech.wvs.movieflix.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.wvs.movieflix.controller.request.MovieRequest;
import tech.wvs.movieflix.controller.response.MovieResponse;
import tech.wvs.movieflix.entity.Category;
import tech.wvs.movieflix.entity.Movie;
import tech.wvs.movieflix.entity.Streaming;
import tech.wvs.movieflix.mapper.MovieMapper;
import tech.wvs.movieflix.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public List<MovieResponse> findAll() {
        return movieRepository.findAll()
                .stream()
                .map(item -> MovieMapper.toResponse(item))
                .toList();
    }

    public Movie create(MovieRequest request) {
        var entity = MovieMapper.toEntity(request);

        entity.setCategories(findValidCategories(request));
        entity.setStreamings(findValidStreamings(request));


        return movieRepository.save(entity);
    }

    private List<Streaming> findValidStreamings(MovieRequest request) {
        List<Streaming> validStreamings = new ArrayList<>();

        request.streamings().forEach(streamingId -> {
            streamingService.findById(streamingId).ifPresent(validStreamings::add);
        });

        return validStreamings;
    }

    private List<Category> findValidCategories(MovieRequest request) {
        List<Category> validCategories = new ArrayList<>();

        request.categories().forEach(categoryId -> {
            categoryService.findById(categoryId).ifPresent(validCategories::add);
        });

        return validCategories;
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public Optional<Movie> update(Long id, MovieRequest request) {
        var entity = movieRepository.findById(id);

        if (entity.isPresent()) {
            updateFields(request, entity);
            movieRepository.save(entity.get());
        }

        return entity;
    }

    private void updateFields(MovieRequest request, Optional<Movie> entity) {
        if (request.title() != null && !request.title().isEmpty()) {
            entity.get().setTitle(request.title());
        }

        if (request.description() != null && !request.description().isEmpty()) {
            entity.get().setDescription(request.description());
        }

        if (request.releaseDate() != null) {
            entity.get().setRelaseDate(request.releaseDate());
        }

        if (request.rating() != null) {
            entity.get().setRating(request.rating());
        }

        if (request.categories() != null && !request.categories().isEmpty()) {
            var categories = findValidCategories(request);
            entity.get().setCategories(categories);
        }

        if (request.streamings() != null && !request.streamings().isEmpty()) {
            var streamings = findValidStreamings(request);
            entity.get().setStreamings(streamings);
        }
    }

    public List<Movie> findByCategory(Long category) {
        return movieRepository.findMovieByCategories(List.of(Category.builder().id(category).build()));
    }

    public boolean deleteById(Long id) {
        var exists = movieRepository.existsById(id);

        if (exists) {
            movieRepository.deleteById(id);
        }

        return exists;
    }
}
