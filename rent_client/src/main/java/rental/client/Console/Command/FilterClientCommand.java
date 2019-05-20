package rental.client.Console.Command;

import org.springframework.web.client.RestTemplate;
import rental.client.Console.InputConverter;
import rental.webgigel.dto.ClientsDto;

import java.util.List;
import java.util.Objects;

public class FilterClientCommand extends Command {

    private RestTemplate template;

    public FilterClientCommand(RestTemplate template) {
        this.template = template;
    }

    @Override
    public void execute(List<String> params) {
//        clientService.filterByName(params.get(0)).forEach(System.out::println);
        Objects.requireNonNull(template.getForObject(
                "http://localhost:8080/api/clients/filter?name=" + params.get(0),
                ClientsDto.class
        )).getClients().forEach(System.out::println);
    }

    @Override
    public Integer paramNr() {
        return 1;
    }

    @Override
    public String params() {
        return "<Str>";
    }
}

