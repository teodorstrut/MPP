package rental.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;
import rental.webgigel.dto.ClientDto;

public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "rental.client.config"
                );

        RestTemplate template = context.getBean(RestTemplate.class);

        ClientDto client = ClientDto.builder()
                .name("ionel")
                .address("str. florilor")
                .build();

        template.postForObject("http://localhost:8080/api/clients",
                client,
                ClientDto.class);

        System.out.println("Client Ended!");
    }
}
