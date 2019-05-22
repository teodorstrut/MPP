package rental.webgigel.converter;

import org.springframework.stereotype.Component;
import rental.core.module.Movie;
import rental.webgigel.dto.MovieDto;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDto> {
    @Override
    public Movie convertDtoToModel(MovieDto dto) {
        Movie movie = Movie.builder()
                .name(dto.getName())
                .build();
        movie.setId(dto.getId());

        return movie;
    }

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        MovieDto dto = MovieDto.builder()
                .name(movie.getName())
                .build();
        dto.setId(movie.getId());

        return dto;
    }
}
