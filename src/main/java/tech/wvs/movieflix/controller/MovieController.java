package tech.wvs.movieflix.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.wvs.movieflix.controller.request.MovieRequest;
import tech.wvs.movieflix.controller.response.MovieResponse;
import tech.wvs.movieflix.mapper.MovieMapper;
import tech.wvs.movieflix.service.MovieService;

import java.net.URI;
import java.util.List;

@Tag(name = "Movie", description = "Recurso responsável por gerenciar os filmes")
@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;


    @Operation(summary = "Salvar filme", description = "Método responsável por salvar um filme",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Filme salvo com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @PostMapping
    public ResponseEntity<MovieResponse> create(@Valid @RequestBody MovieRequest request) {
        var entity = movieService.create(request);

        return ResponseEntity.created(URI.create("/movieflix/movie/" + entity.getId())).body(MovieMapper.toResponse(entity));
    }

    @Operation(summary = "Buscar filmes", description = "Método responsável por buscar todos os filmes",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os filmes cadastrados",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @Operation(summary = "Buscar filme por id", description = "Método responsável por buscar um filme por id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme encontrado com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
    @GetMapping(path = "/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id) {
        var entity = movieService.findById(id);

        return entity.isPresent() ?
                ResponseEntity.ok(MovieMapper.toResponse(entity.get())) :
                ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @Valid @RequestBody MovieRequest request) {
        var entity = movieService.update(id, request);

        return entity.isPresent() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category) {
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
