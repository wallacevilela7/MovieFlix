package tech.wvs.movieflix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.wvs.movieflix.controller.request.StreamingRequest;
import tech.wvs.movieflix.controller.response.StreamingResponse;
import tech.wvs.movieflix.entity.Streaming;
import tech.wvs.movieflix.mapper.StreamingMapper;
import tech.wvs.movieflix.service.StreamingService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {
    private final StreamingService streamingService;

    @PostMapping
    public ResponseEntity<StreamingResponse> create(@Valid @RequestBody StreamingRequest request) {
        var created = streamingService.create(request);
        return ResponseEntity.created(URI.create("/movieflix/streaming/" + created.getId())).build();
    }

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> findAll() {
        return ResponseEntity.ok(streamingService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StreamingResponse> findById(@PathVariable("id") Long id) {
        var entity = streamingService.findById(id);

        return entity.isPresent() ?
                ResponseEntity.ok(StreamingMapper.toResponse(entity.get())) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var deleted = streamingService.deleteById(id);
        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody StreamingRequest request){
        var entity = streamingService.update(id, request);

        return entity.isPresent() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
