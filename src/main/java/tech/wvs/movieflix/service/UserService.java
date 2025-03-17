package tech.wvs.movieflix.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.wvs.movieflix.controller.request.UserRequest;
import tech.wvs.movieflix.entity.User;
import tech.wvs.movieflix.mapper.UserMapper;
import tech.wvs.movieflix.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(UserRequest userRequest) {
        var user = UserMapper.toEntity(userRequest);
        return userRepository.save(user);
    }
}
