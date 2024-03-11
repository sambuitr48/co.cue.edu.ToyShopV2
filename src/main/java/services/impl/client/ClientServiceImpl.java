package services.impl.client;

import models.Client;
import repository.Repository;
import repository.impl.client.ClientRepositoryJDBCImpl;
import services.Service;

import java.util.List;

public class ClientServiceImpl implements Service<Client> {
    private Repository<Client> clientRepository;

    public ClientServiceImpl() {
        this.clientRepository = new ClientRepositoryJDBCImpl();
    }

    @Override
    public void add(Client client) {

    }

    @Override
    public List<Client> listAll() {
        return null;
    }
}