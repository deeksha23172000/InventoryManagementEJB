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
 @NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i")
 @NamedQuery(name = "Inventory.getByName", query = "SELECT i from Inventory i where i.name = :name")
 @NamedQuery(name = "Inventory.clearAll", query = "DELETE FROM Inventory")
 public class Inventory implements Comparable<Inventory>, Serializable{
 @Id
 @GeneratedValue
 private Long id;
 @Column(unique = true)
 private String name;
 private String sport;
 private int quantity;
 private double price;
 @ManyToOne
 @JoinColumn(name="id_store")
 private Store store;
 private Date inventoryDate;


 @PrePersist
 void createdAt(){ this.inventoryDate=new Date(); }

 public Inventory(String name,String sport, int quantity, double price){
         this.name=name;
         this.sport=sport;
         this.quantity=quantity;
         this.price=price;
 }

 @PreUpdate
 void updatedAt(){
         this.inventoryDate=new Date();
 }

 @Override
 public int compareTo(Inventory i){
         return inventoryDate.compareTo(i.inventoryDate);
 }
 }