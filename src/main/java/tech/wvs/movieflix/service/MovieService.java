package tech.wvs.movieflix.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.wvs.movieflix.controller.request.MovieRequest;
import tech.wvs.movieflix.controller.response.MovieResponse;
import tech.wvs.movieflix.entity.Movie;
import tech.wvs.movieflix.mapper.MovieMapper;
import tech.wvs.movieflix.repository.MovieRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;


    public Movie create(MovieRequest request) {
        return movieRepository.save(MovieMapper.toEntity(request));
    }

    public List<MovieResponse> findAll() {
        return movieRepository.findAll()
                .stream()
                .map(item -> MovieMapper.toResponse(item))
                .toList();
    }
}
