package com.Deeksha.store;
import com.Deeksha.entity.Store;

import java.util.List;

public interface StoreService {
    void clearList();

    List<Store> getStoreList();

    void addToList(Store store);
    void removeFromList(Store store);
}
