package Application.controller;

import Application.entity.Clients;
import Application.exception.NotFoundException;
import Application.service.ClientsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientsService clientsService;

    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Clients>> getAllClients() {
        List<Clients> list = clientsService.getAllClients();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clients> getClientById(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(clientsService.getClientById(id), HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<Clients>> getClientByName(
            @RequestParam("first-name") String firstName,
            @RequestParam("second-name") String secondName,
            @RequestParam("pather-name") String patherName
    ) {
        try {
            return new ResponseEntity<>(
                    clientsService.getClientByName(firstName, secondName, patherName), HttpStatus.OK
            );
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PostMapping(value = "/new", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<Clients> addBook(@RequestBody Clients client) {
        return new ResponseEntity<>(clientsService.addClient(client), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<Void> updateClient(@PathVariable("id") long id, @RequestBody Clients client) {
        try {
            clientsService.updateClient(id, client);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") long id) {
        clientsService.deleteClient(id);
        try {
            clientsService.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

}
