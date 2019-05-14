package rental.client.Console;

import org.springframework.web.client.RestTemplate;
import rental.client.Console.Command.*;
import rental.client.exception.UserInputException;
import rental.client.exception.ValidatorException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The main class for the console-based UI
 */
public class Console {

    private Map<String, Command> commands;

    private RestTemplate template;

    public Console(RestTemplate template) {
        this.template=template;
        this.commands = new HashMap<>();

        commands.put("addclient", new AddClientCommand(template));
        commands.put("addmovie", new AddMovieCommand(template));
        commands.put("printallclients", new PrintAllClientsCommand(template));
        commands.put("sortclients", new SortClientCommand(template));
        commands.put("deletemovie", new DeleteObjectCommand(template,"movies"));
        commands.put("deleteclient", new DeleteObjectCommand(template,"clients"));
        commands.put("updateclient", new UpdateClientCommand(template));
        commands.put("filterclientname", new FilterClientCommand(template));
    }

    /**
     * Takes the user input and executes the command corresponding to it
     *
     * @param s the user input
     * @throws UserInputException if the user issues an invalid command(wrong keyword, wrong parameters)
     */
    private void executeCommand(String s) throws UserInputException {

        Optional<Command> cmd;
        List<String> l = Arrays.asList(s.split(" ", 2));

        try {
            cmd = Optional.of(commands.get(l.get(0)));
        } catch (NullPointerException e) {
            throw new UserInputException("Command not recognised!");
        }

        List<String> params = l.size() == 1 ? new ArrayList<>() : Arrays.asList(l.get(1).split(","));
        params = params.stream().map(String::trim).collect(Collectors.toList());

        if (params.size() != cmd.get().paramNr())
            throw new UserInputException("Invalid number of parameters!");

        try {
            cmd.get().execute(params);
        } catch (ValidatorException e) {
            throw new UserInputException(e.getMessage());
        }


    }

    /**
     * Starts the console-based UI
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Insert your command: ");
            String cmd = sc.nextLine();
            try {
                if (cmd.equals("exit"))
                    break;

                if (cmd.equals("help")) {
                    commands.keySet().stream().map(s->s+" "+commands.get(s).params()).forEach(System.out::println);
                    System.out.println("exit");
                    continue;
                }

                executeCommand(cmd);
            } catch (UserInputException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
