package de.ibb.web.aufbau.relationen;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jwebak07_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private final List<Order> orders = new ArrayList<>();

    public User() {}
    public User(String username) { this.username = username; }
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public List<Order> getOrders() { return orders; }
}
