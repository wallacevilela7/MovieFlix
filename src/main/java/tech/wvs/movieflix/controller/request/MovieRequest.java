package tech.wvs.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(
        @Schema(type = "string", description = "Titulo do filme", example = "O Poderoso Chefão")
        @NotEmpty(message = "Titulo do filme é obrigatorio")
        String title,

        @Schema(type = "string", description = "Descrição do filme", example = "O Poderoso Chefão é um filme de máfia americano de 1972 dirigido por Francis Ford Coppola")
        String description,

        @Schema(type = "date", description = "Data de lançamento", example = "07/07/1972")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,

        @Schema(type = "double", description = "Avaliação do filme", example = "9.2")
        Double rating,

        @Schema(type = "array", description = "Lista dos ids das categorias", example = "[1,2,3]")
        List<Long> categories,

        @Schema(type = "array", description = "Lista dos ids dos serviços de streaming", example = "[1,2,3]")
        List<Long> streamings) {
}
