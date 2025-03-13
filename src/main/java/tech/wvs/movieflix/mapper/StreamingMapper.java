package tech.wvs.movieflix.mapper;

import lombok.experimental.UtilityClass;
import tech.wvs.movieflix.controller.request.StreamingRequest;
import tech.wvs.movieflix.controller.response.StreamingResponse;
import tech.wvs.movieflix.entity.Streaming;

@UtilityClass
public class StreamingMapper {
    public static Streaming toEntity(StreamingRequest request) {
        return Streaming.builder()
                .name(request.name())
                .build();
    }

    public static StreamingResponse toResponse(Streaming entity) {
        return StreamingResponse
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
