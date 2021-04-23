package com.Deeksha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s")
@NamedQuery(name = "Store.getByName", query = "SELECT s from Store s where s.name = :name")
@NamedQuery(name = "Store.clearAll", query = "DELETE FROM Store")
public class Store implements Comparable<Store>, Serializable{
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private String location;
    private Date storeDate;

    @OneToMany(mappedBy = "store", fetch = FetchType.EAGER)
    private List<Inventory> listOfInventory;
    @PrePersist
    void createdAt() {
        this.storeDate = new Date();
    }

    public Store(String name, String location) {
        this.name = name;
       this.location=location;
    }

    @PreUpdate
    void updatedAt() {
        this.storeDate = new Date();
    }

    @Override
    public int compareTo(Store o) {
        return storeDate.compareTo(o.storeDate);
    }
}
