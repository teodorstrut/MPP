package rental.core.service;

import org.springframework.stereotype.Service;
import rental.core.module.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import rental.core.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {
    private static final Logger log = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    MovieRepository repo;

    @Override
    public List<Movie> getAll() {
        log.trace("MovieService - getAll() method entered");

        List<Movie> result=repo.findAll();

        log.trace("getAll: result={}",result);

        return result;
    }

    @Override
    public Movie add(Movie elem) {
        log.trace("MovieService - add() method entered");

        Movie result=repo.save(elem);

        log.trace("add: result={}",result);

        return result;
    }

    @Override
    public void remove(Long l) {
        log.trace("MovieService delete: id={}", l);

        repo.deleteById(l);

        log.trace("delete --- method finished");
    }

    @Override
    @Transactional
    public Movie update(Long l, Movie elem) {
        log.trace("MovieService update: id={},movie={}", l, elem);

        Optional<Movie> optionalMovie = repo.findById(l);
        Movie result = optionalMovie.orElse(elem);
        result.setName(elem.getName());
        result.setDate(elem.getDate());

        log.trace("update: result={}", result);

        return result;
    }
}
