package rental.client.Console.Command;

import org.springframework.web.client.RestTemplate;
import rental.client.Console.InputConverter;

import rental.webgigel.dto.ClientDto;

import java.util.List;

/**
 * Command for updating a client
 */
public class UpdateClientCommand extends Command {
    private RestTemplate template;

    public UpdateClientCommand(RestTemplate template) {

        this.template = template;
    }

    /**
     * Executes the command
     *
     * @param params the user-given parameters
     */
    @Override
    public void execute(List<String> params) {
//        Client client = new Client(InputConverter.readInt(params.get(0)), params.get(1), params.get(2));
//        clientService.update(client).orElseThrow(() -> new IllegalArgumentException("invalid update! client with given id does not exist!"));
        ClientDto client = ClientDto.builder()
                .name(params.get(1))
                .address(params.get(2))
                .build();

        client.setId(Long.valueOf(InputConverter.readInt(params.get(0))));
        template.put(
                "http://localhost:8080/api/students/{id}",
                client,
                client.getId()
        );
    }

    /**
     * @return 3
     */
    @Override
    public Integer paramNr() {
        return 3;
    }

    @Override
    public String params() {
        return "<ID>,<Name>,<Address>";
    }
}
