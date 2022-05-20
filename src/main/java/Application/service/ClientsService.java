package Application.service;

import Application.entity.Clients;
import Application.exception.NotFoundException;
import Application.repository.ClientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsService {

    private final ClientsRepository clientsRepository;

    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public List<Clients> getAllClients() {
        return clientsRepository.findAll();
    }

    public Clients getClientById(long id) {
        Optional<Clients> optionalBookTypes = clientsRepository.findById(id);
        if (optionalBookTypes.isPresent()) {
            return optionalBookTypes.get();
        } else {
            throw new NotFoundException("Client not found");
        }
    }

    public List<Clients> getClientByName(
            String firstName,
            String secondsName,
            String patherName
    ) {
        List<Clients> clients = clientsRepository.findByFullName(firstName, secondsName, patherName);
        if (!clients.isEmpty()) {
            return clients;
        } else {
            throw new NotFoundException("Client not found");
        }
    }

    public Clients addClient(Clients client) {
        return clientsRepository.save(client);
    }

    public void deleteClient(long id) {
        Clients client;
        try {
            client = getClientById(id);
        } catch (NotFoundException exception) {
            throw new NotFoundException("Client not found");
        }
        clientsRepository.delete(client);
    }

    public void updateClient(long id, Clients client) {
        try {
            getClientById(id);
        } catch (NotFoundException exception) {
            throw new NotFoundException("Client not found");
        }
        clientsRepository.updateById(
                client.getFirst_name(),
                client.getLast_name(),
                client.getPather_name(),
                client.getPassport_number(),
                client.getPassport_seria(),
                id);
    }
}
