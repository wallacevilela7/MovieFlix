package tech.wvs.movieflix.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import tech.wvs.movieflix.exception.UsernameOrPasswordException;

@ControllerAdvice
public class ApplicaticationControllerAdcive {

    @ExceptionHandler(UsernameOrPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotFoundException(UsernameOrPasswordException exception) {
        return exception.getMessage();
    }

}
