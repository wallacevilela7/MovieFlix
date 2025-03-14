package tech.wvs.movieflix.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.wvs.movieflix.controller.request.MovieRequest;
import tech.wvs.movieflix.controller.response.MovieResponse;
import tech.wvs.movieflix.mapper.MovieMapper;
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

        return ResponseEntity.created(URI.create("/movieflix/movie/" + entity.getId())).body(MovieMapper.toResponse(entity));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll(){
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id) {
        var entity = movieService.findById(id);

        return entity.isPresent() ?
                ResponseEntity.ok(MovieMapper.toResponse(entity.get())) :
                ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                                @RequestBody MovieRequest request) {
        var entity = movieService.update(id, request);

        return entity.isPresent() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category){
            var items = movieService.findByCategory(category);

            return !items.isEmpty() ?
                    ResponseEntity.ok(items.stream().map(item -> MovieMapper.toResponse(item)).toList()) :
                    ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        var deleted = movieService.deleteById(id);

        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
