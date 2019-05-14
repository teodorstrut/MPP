package rental.client.Console.Command;

import org.springframework.web.client.RestTemplate;

import java.util.List;

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
//        clientService.getAll().forEach(System.out::println);
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
