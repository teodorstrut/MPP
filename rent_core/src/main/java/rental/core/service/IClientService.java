package rental.core.service;

import rental.core.module.BaseEntity;
import rental.core.module.Client;

import java.io.Serializable;
import java.util.List;

public interface IClientService {
    List<Client> getAll();

    List<Client> filter(String name);

    Client add(Client elem);

    void remove(Long id);

    Client update(Long id, Client elem);

}
