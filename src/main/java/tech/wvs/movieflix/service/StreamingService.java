package tech.wvs.movieflix.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.wvs.movieflix.controller.request.StreamingRequest;
import tech.wvs.movieflix.controller.response.MovieResponse;
import tech.wvs.movieflix.controller.response.StreamingResponse;
import tech.wvs.movieflix.entity.Streaming;
import tech.wvs.movieflix.mapper.MovieMapper;
import tech.wvs.movieflix.mapper.StreamingMapper;
import tech.wvs.movieflix.repository.StreamingRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {
    private final StreamingRepository streamingRepository;

    public List<StreamingResponse> findAll() {
        return streamingRepository.findAll()
                .stream()
                .map(item -> StreamingMapper.toResponse(item))
                .toList();
    }

    public Streaming create(StreamingRequest request) {
        var entity = StreamingMapper.toEntity(request);
        return streamingRepository.save(entity);
    }

    public Optional<Streaming> findById(Long id) {
        return streamingRepository.findById(id);
    }

    public boolean deleteById(Long id) {
        var exists = streamingRepository.existsById(id);
        if (exists) {
            streamingRepository.deleteById(id);
        }

        return exists;
    }
}
