package tech.wvs.movieflix.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.wvs.movieflix.config.TokenService;
import tech.wvs.movieflix.controller.request.LoginRequest;
import tech.wvs.movieflix.controller.request.UserRequest;
import tech.wvs.movieflix.controller.response.LoginResponse;
import tech.wvs.movieflix.controller.response.UserResponse;
import tech.wvs.movieflix.entity.User;
import tech.wvs.movieflix.mapper.UserMapper;
import tech.wvs.movieflix.service.UserService;

@RestController
@RequestMapping(path = "/movieflix/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping(path = "/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
        var entity = userService.create(userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(entity));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authenticate = authenticationManager.authenticate(userAndPass);

        User user = (User) authenticate.getPrincipal();

        //gerar o token JWT
        String token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
