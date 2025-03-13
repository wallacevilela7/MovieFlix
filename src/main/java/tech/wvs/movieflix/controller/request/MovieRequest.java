package tech.wvs.movieflix.controller.request;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(String title,
                           String description,
                           LocalDate releaseDate,
                           Double rating,
                           List<Long> categories,
                           List<Long> streamings) {
}
