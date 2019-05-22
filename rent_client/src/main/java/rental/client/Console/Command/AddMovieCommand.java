package rental.client.Console.Command;

import org.springframework.web.client.RestTemplate;
import rental.client.Console.InputConverter;
import rental.webgigel.dto.MovieDto;

import java.util.List;

public class AddMovieCommand extends Command{
    private RestTemplate template;

    public AddMovieCommand(RestTemplate template) {
        this.template = template;
    }

    /**
     * Executes the command
     * @param params the user-given parameters
     */
    @Override
    public void execute(List<String> params) {
        MovieDto movie = MovieDto.builder()
                .name(params.get(0))
                .build();

        template.postForObject("http://localhost:8080/api/movies",
                movie,
                MovieDto.class);
    }

    /**
     * Returns the expected number of parameters
     * @return 2
     */
    @Override
    public Integer paramNr() {
        return 1;
    }

    @Override
    public String params() {
        return "<Name>";
    }
}
