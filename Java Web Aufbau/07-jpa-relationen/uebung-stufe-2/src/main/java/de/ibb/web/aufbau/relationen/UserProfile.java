package de.ibb.web.aufbau.relationen;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "jwebak07_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;

    @OneToOne(mappedBy = "profile")
    private User user;

    public UserProfile() {}
    public UserProfile(String city) { this.city = city; }
    public Long getId() { return id; }
    public String getCity() { return city; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
