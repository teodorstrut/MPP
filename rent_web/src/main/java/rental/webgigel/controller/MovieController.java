package rental.webgigel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rental.core.module.Movie;
import rental.core.service.IMovieService;
import rental.webgigel.converter.MovieConverter;
import rental.webgigel.dto.MovieDto;
import rental.webgigel.dto.MoviesDto;

import java.util.List;
import java.util.Set;

@RestController
public class MovieController {
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    IMovieService service;

    @Autowired
    MovieConverter converter;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    MoviesDto getAllMovies(){
        log.trace("getAllCMovies() --- method started");
        List<Movie> movies = service.getAll();
        Set<MovieDto> dtos = converter.convertModelsToDtos(movies);
        MoviesDto result = new MoviesDto(dtos);

        log.trace("getAllMovies: result={}", result);

        return result;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    MovieDto saveMovie(@RequestBody MovieDto dto) {
        log.trace("saveMovie: dto={}", dto);

        Movie saved = this.service.add(
                converter.convertDtoToModel(dto)
        );
        MovieDto result = converter.convertModelToDto(saved);

        log.trace("saveMovie: result={}", result);

        return result;
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    MovieDto updateMovie(@PathVariable Long id,
                             @RequestBody MovieDto dto) {
        log.trace("updateMovie: id={},dto={}", id, dto);

        Movie update = service.update(
                id,
                converter.convertDtoToModel(dto));
        MovieDto result = converter.convertModelToDto(update);

        return result;
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        log.trace("deleteMovie: id={}", id);

        service.remove(id);

        log.trace("deleteMovie --- method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
