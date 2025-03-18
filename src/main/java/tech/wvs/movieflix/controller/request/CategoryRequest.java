package tech.wvs.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(@NotEmpty(message = "nome da categoria é obrigatorio") String name) {
}
