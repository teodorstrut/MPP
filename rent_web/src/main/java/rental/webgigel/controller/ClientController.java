package rental.webgigel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rental.core.module.Client;
import rental.core.service.IClientService;
import rental.webgigel.converter.ClientConverter;
import rental.webgigel.dto.ClientDto;
import rental.webgigel.dto.ClientsDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class ClientController {
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    IClientService service;

    @Autowired
    ClientConverter converter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    ClientsDto getAllClients(){
        log.trace("getAllClients() --- method started");
        List<Client> clients = service.getAll();
        Set<ClientDto> dtos = converter.convertModelsToDtos(clients);
        ClientsDto result = new ClientsDto(dtos);

        log.trace("getAllClients: result={}", result);

        return result;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    ClientDto addClient(@RequestBody ClientDto dto) {
        log.trace("addClient: dto={}", dto);

        Client saved = this.service.add(
                converter.convertDtoToModel(dto)
        );
        ClientDto result = converter.convertModelToDto(saved);

        log.trace("addlient: result={}", result);

        return result;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    ClientDto updateClient(@PathVariable Long id,
                             @RequestBody ClientDto dto) {
        log.trace("updateClient: id={},dto={}", id, dto);

        Client update = service.update(
                id,
                converter.convertDtoToModel(dto));
        ClientDto result = converter.convertModelToDto(update);

        return result;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id) {
        log.trace("deleteClient: id={}", id);

        service.remove(id);

        log.trace("deleteClient --- method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
