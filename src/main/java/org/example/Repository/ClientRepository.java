package org.example.Repository;

import org.example.Tables.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ClientRepository {

    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private EntityManager entityManager;

    public List<Client> getAllClients() {
        TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM Client c", Client.class);
        return query.getResultList();
    }

    public Client getClientById(int id) {
        return entityManager.find(Client.class, id);
    }

    @Transactional
    public Client createClient(Client client) {
        entityManager.persist(client);
        return client;
    }

    @Transactional
    public Client updateClient(int id, Client updatedClient) {
        Client existingClient = entityManager.find(Client.class, id);
        if (existingClient != null) {
            existingClient.setName(updatedClient.getName());
            existingClient.setSurname(updatedClient.getSurname());
            existingClient.setPhoneNumber(updatedClient.getPhoneNumber());
        }
        return existingClient;
    }

    @Transactional
    public void deleteClient(int id) {
        Client client = entityManager.find(Client.class, id);
        if (client != null) {
            entityManager.remove(client);
        }
    }
}
