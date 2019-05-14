package rental.client.Console.Command;

import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Command for sorting the clients alphabetically after their names
 */
public class SortClientCommand extends Command {
    private RestTemplate template;

    public SortClientCommand(RestTemplate template){
        this.template=template;
    }

    /**
     * Executes the command
     * @param params the user-given parameters
     */
    @Override
    public void execute(List<String> params){
//        clientService.sortAlpha().forEach(System.out::println);
    }

    /**
     * Returns the expected number of parameters
     * @return 0
     */
    @Override
    public Integer paramNr(){
        return 0;
    }

    @Override
    public String params() {
        return "";
    }
}
