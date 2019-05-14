package rental.client.Console.Command;

import org.springframework.web.client.RestTemplate;
import rental.client.Console.InputConverter;

import java.util.List;

/**
 * command for deleting a movie
 */
public class DeleteObjectCommand extends Command{
    private RestTemplate template;
    private String target;

    public DeleteObjectCommand(RestTemplate template,String target){
        this.template=template;
        this.target=target;
    }

    /**
     * executes the command
     *
     * @param params the user-given parameters
     */
    @Override
    public void execute(List<String> params){
//        this.service.delete(InputConverter.readInt(params.get(0)));
                template.delete(
                "http://localhost:8080/api/"+target+"/{id}",
                InputConverter.readInt(params.get(0))
        );
    }

    /**
     * Returns the expected number of parameters
     * @return 1
     */
    @Override
    public Integer paramNr() {
        return 1;
    }

    @Override
    public String params() {
        return "<ID>";
    }
}
