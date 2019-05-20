package rental.client.Console.Command;

import org.springframework.web.client.RestTemplate;
import rental.webgigel.dto.ClientsDto;

import java.util.List;
import java.util.Objects;

/**
 * Command for printing all clients
 */

public class PrintAllClientsCommand extends Command {

    private RestTemplate template;

    public PrintAllClientsCommand(RestTemplate template) {
        this.template = template;
    }

    /**
     * Executes the command
     * @param params the user-given parameters
     */
    @Override
    public void execute(List<String> params) {
        Objects.requireNonNull(template.getForObject(
                "http://localhost:8080/api/clients",
                ClientsDto.class
        )).getClients().forEach(System.out::println);
    }

    /**
     * Returns the expected number of parameters
     * @return 0
     */
    @Override
    public Integer paramNr() {
        return 0;
    }

    @Override
    public String params() {
        return "";
    }
}
