package tech.wvs.movieflix.mapper;

import lombok.experimental.UtilityClass;
import tech.wvs.movieflix.controller.request.CategoryRequest;
import tech.wvs.movieflix.controller.response.CategoryResponse;
import tech.wvs.movieflix.entity.Category;

@UtilityClass
public class CategoryMapper {
    public static Category toEntity(CategoryRequest request){
        return Category
                .builder()
                .name(request.name())
                .build();
    }

    public static CategoryResponse toResponse(Category category){
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
