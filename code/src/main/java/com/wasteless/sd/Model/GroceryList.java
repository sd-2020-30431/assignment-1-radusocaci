package com.wasteless.sd.Model;

import javax.persistence.*;

@Entity
@Table(name = "grocerylist")
public class GroceryList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userId) {
        this.username = userId;
    }

    @Override
    public String toString() {
        return "GroceryList{" +
                "id=" + id +
                ", userId=" + username +
                '}';
    }
}
