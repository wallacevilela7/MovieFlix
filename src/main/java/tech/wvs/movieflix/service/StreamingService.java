package tech.wvs.movieflix.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.wvs.movieflix.controller.request.StreamingRequest;
import tech.wvs.movieflix.entity.Streaming;
import tech.wvs.movieflix.mapper.StreamingMapper;
import tech.wvs.movieflix.repository.StreamingRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {
    private final StreamingRepository streamingRepository;

    public List<Streaming> findAll() {
        return streamingRepository.findAll();
    }

    public Streaming create(StreamingRequest request) {
        var category = StreamingMapper.toEntity(request);
        return streamingRepository.save(category);
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
