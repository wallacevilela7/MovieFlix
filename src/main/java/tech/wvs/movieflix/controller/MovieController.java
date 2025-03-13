package tech.wvs.movieflix.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.wvs.movieflix.controller.request.MovieRequest;
import tech.wvs.movieflix.controller.response.MovieResponse;
import tech.wvs.movieflix.service.MovieService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;


    @PostMapping
    public ResponseEntity<MovieResponse> create(@RequestBody MovieRequest request) {
        var entity = movieService.create(request);

        return ResponseEntity.created(URI.create("/movieflix/movie/" + entity.getId())).build();
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll(){
        return ResponseEntity.ok(movieService.findAll());
    }
}
