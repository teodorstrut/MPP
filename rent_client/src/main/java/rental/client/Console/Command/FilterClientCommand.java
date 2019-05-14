package rental.client.Console.Command;

import org.springframework.web.client.RestTemplate;

import java.util.List;

public class FilterClientCommand extends Command {

    private RestTemplate template;

    public FilterClientCommand(RestTemplate template) {
        this.template = template;
    }

    @Override
    public void execute(List<String> params) {
//        clientService.filterByName(params.get(0)).forEach(System.out::println);
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

