package rental.core.service;

import rental.core.module.Movie;

import java.util.List;

public interface IMovieService {
    List<Movie> getAll();

    Movie add(Movie elem);

    void remove(Long id);

    Movie update(Long id, Movie elem);

}
