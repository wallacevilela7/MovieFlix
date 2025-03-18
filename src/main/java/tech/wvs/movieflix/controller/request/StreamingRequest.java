package tech.wvs.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record StreamingRequest(@NotEmpty(message = "Nome do streaming é obrigatorio") String name) {
}
