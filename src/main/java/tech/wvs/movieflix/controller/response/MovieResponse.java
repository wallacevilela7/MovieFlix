package tech.wvs.movieflix.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(
        @Schema(type = "long", description = "Codigo identificador do filme", example = "1")
        Long id,

        @Schema(type = "string", description = "Titulo do filme", example = "O Poderoso Chefão")
        String title,

        @Schema(type = "string", description = "Descrição do filme", example = "O Poderoso Chefão é um filme de máfia americano de 1972 dirigido por Francis Ford Coppola")
        String description,

        @Schema(type = "date", description = "Data de lançamento", example = "07/07/1972")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,

        @Schema(type = "double", description = "Avaliação do filme", example = "9.2")
        Double rating,

        @Schema(type = "array", description = "Lista das categorias")
        List<CategoryResponse> categories,

        @Schema(type = "array", description = "Lista dos serviços de streaming")
        List<StreamingResponse> streamings) {
}
