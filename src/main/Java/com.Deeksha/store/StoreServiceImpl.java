package com.Deeksha.store;

import com.Deeksha.entity.Store;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StoreServiceImpl implements StoreService{
    private static final int MAX_CAPACITY = 27;
    private static final int INITIAL_CAPACITY = 18;

    @PersistenceContext
    private EntityManager em;


    @Override
    public void clearList() {
        Query deleteFromStore = em.createNamedQuery("Store.clearAll");
        deleteFromStore.executeUpdate();
    }

    @Override
    public List<Store> getStoreList() {
        List<Store> storeList =  em.createNamedQuery("Store.findAll", Store.class)
                .getResultList();
            return storeList;
    }

    @Override
    public void addToList(Store store) {
        em.persist(store);
    }

    @Override
    public void removeFromList(Store store) {
        Store correspondingStore = em.createNamedQuery("Store.getByName", Store.class)
                .setParameter("name", store.getName())
                .getSingleResult();
        em.remove(correspondingStore);
    }
}
