package rental.client.Console.Command;

import org.springframework.web.client.RestTemplate;
import rental.webgigel.dto.ClientDto;

import java.util.List;

/**
 * Command for adding a client
 */
public class AddClientCommand extends Command {

    private RestTemplate template;

    public AddClientCommand(RestTemplate template) {
        this.template = template;
    }

    /**
     * Executes the command
     * @param params the user-given parameters
     */
    @Override
    public void execute(List<String> params) {
        ClientDto client = ClientDto.builder()
                .name(params.get(0))
                .address(params.get(1))
                .build();

                template.postForObject("http://localhost:8080/api/clients",
                client,
                ClientDto.class);
    }

    /**
     * Returns the expected number of parameters
     * @return 2
     */
    @Override
    public Integer paramNr() {
        return 2;
    }

    @Override
    public String params() {
        return "<Name>,<Address>";
    }
}
