package tech.wvs.movieflix.mapper;

import lombok.experimental.UtilityClass;
import org.apache.coyote.Request;
import tech.wvs.movieflix.controller.request.MovieRequest;
import tech.wvs.movieflix.controller.response.CategoryResponse;
import tech.wvs.movieflix.controller.response.MovieResponse;
import tech.wvs.movieflix.controller.response.StreamingResponse;
import tech.wvs.movieflix.entity.Category;
import tech.wvs.movieflix.entity.Movie;
import tech.wvs.movieflix.entity.Streaming;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static Movie toEntity(MovieRequest movieRequest) {

        List<Category> listCategories = movieRequest.categories().stream()
                .map(categoryId ->
                        Category
                                .builder()
                                .id(categoryId)
                                .build())
                .toList();


        List<Streaming> listStreamings = movieRequest.streamings().stream()
                .map(streamingId ->
                        Streaming
                                .builder()
                                .id(streamingId)
                                .build())
                .toList();

        return Movie
                .builder()
                .title(movieRequest.title())
                .description(movieRequest.description())
                .relaseDate(movieRequest.releaseDate())
                .rating(movieRequest.rating())
                .categories(listCategories)
                .streamings(listStreamings)
                .build();
    }

    public static MovieResponse toResponse(Movie movie) {
        List<CategoryResponse> listCategory = movie.getCategories().stream()
                .map(category -> CategoryMapper.toResponse(category))
                .toList();

        List<StreamingResponse> listStreaming = movie.getStreamings().stream()
                .map(streaming -> StreamingMapper.toResponse(streaming))
                .toList();

        return MovieResponse
                .builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getRelaseDate())
                .rating(movie.getRating())
                .categories(listCategory)
                .streamings(listStreaming)
                .build();
    }
}
