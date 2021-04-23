package com.Deeksha.inventory;

import com.Deeksha.entity.Inventory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Remote(InventoryService.class)
public class InventoryServiceImpl implements InventoryService {
    private static final int MAX_CAPACITY = 27;
    private static final int INITIAL_CAPACITY = 18;

    @PersistenceContext
    private EntityManager em;


    @Override
    public void clearList() {
        Query deleteFromInventory = em.createNamedQuery("Inventory.clearAll");
        deleteFromInventory.executeUpdate();
    }

    @Override
    public List<Inventory> getInventoryList() {
        List<Inventory> inventoryList =  em.createNamedQuery("Inventory.findAll", Inventory.class)
                .getResultList();
            return inventoryList;
    }

    @Override
    public void addToList(Inventory inventory) {
        em.persist(inventory);
    }

    @Override
    public void removeFromList(Inventory inventory) {
        Inventory correspondingInventory = em.createNamedQuery("Inventory.getByName", Inventory.class)
                .setParameter("name", inventory.getName())
                .getSingleResult();
        em.remove(correspondingInventory);
    }

}
