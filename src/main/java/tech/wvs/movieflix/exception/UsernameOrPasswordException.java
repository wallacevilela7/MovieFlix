package tech.wvs.movieflix.exception;

public class UsernameOrPasswordException extends RuntimeException {
    public UsernameOrPasswordException(String message) {
        super(message);
    }
}
