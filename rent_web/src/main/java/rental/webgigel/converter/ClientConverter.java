package rental.webgigel.converter;

import org.springframework.stereotype.Component;
import rental.core.module.Client;
import rental.webgigel.dto.ClientDto;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        Client client = Client.builder()
                .name(dto.getName())
                .adress(dto.getAddress())
                .build();

        client.setId(dto.getId());
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto dto = ClientDto.builder()
                .name(client.getName())
                .address(client.getAdress())
                .build();

        dto.setId(client.getId());
        return dto;
    }
}
