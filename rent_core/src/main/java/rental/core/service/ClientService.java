package rental.core.service;

import org.springframework.stereotype.Service;
import rental.core.module.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import rental.core.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    ClientRepository repo;

    @Override
    public List<Client> getAll() {
        log.trace("ClientService - getAll() method entered");

        List<Client> result=repo.findAll();

        log.trace("getAll: result={}",result);

        return result;
    }

    @Override
    public Client add(Client elem) {
        log.trace("ClientService - add() method entered");

        Client result=repo.save(elem);

        log.trace("add: result={}",result);

        return result;
    }

    @Override
    public void remove(Long l) {
        log.trace("MovieService delete: id={}", l);

        repo.deleteById(l);

        log.trace("delete --- method finished");
    }

    @Override
    @Transactional
    public Client update(Long l, Client elem) {
        log.trace("MovieService update: id={},movie={}", l, elem);

        Optional<Client> optionalClient = repo.findById(l);
        Client result = optionalClient.orElse(elem);
        result.setName(elem.getName());
        result.setAdress(elem.getAdress());

        log.trace("update: result={}", result);

        return result;
    }
}
