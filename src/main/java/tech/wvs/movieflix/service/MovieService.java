package tech.wvs.movieflix.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.wvs.movieflix.repository.MovieRepository;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
}
