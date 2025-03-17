package tech.wvs.movieflix.mapper;

import lombok.experimental.UtilityClass;
import tech.wvs.movieflix.controller.request.UserRequest;
import tech.wvs.movieflix.controller.response.UserResponse;
import tech.wvs.movieflix.entity.User;

@UtilityClass
public class UserMapper {

    public static User toEntity(UserRequest request){
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public static UserResponse toResponse(User entity){
        return UserResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }
}
