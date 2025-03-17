package tech.wvs.movieflix.controller.request;

public record UserRequest(String name,
                          String email,
                          String password) {
}
