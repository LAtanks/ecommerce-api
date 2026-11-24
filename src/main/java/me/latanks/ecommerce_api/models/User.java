package me.latanks.ecommerce_api.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import me.latanks.ecommerce_api.models.enums.Roles;
import org.hibernate.validator.constraints.br.CPF;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String username;

    @NotEmpty
    private String fullName;

    @NotEmpty
    @NotNull

    private String password;

    @Email
    private String email;

    @CPF
    private String cpf;

    @NotNull
    @NotEmpty
    private String phone;

    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private String state;

    @ManyToOne(fetch = FetchType.EAGER)
    private Set<Purchase> purchasedProducts;

    @ManyToOne(fetch = FetchType.EAGER)
    private Set<Product> cart;

    private Set<Roles>  roles = new HashSet<>();

    public User(Long id, String username, String fullName, String password, String email, String cpf, String phone, String address, String city, String state, Set<Purchase> purchasedProducts, Set<Product> cart, Set<Roles> roles) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.purchasedProducts = purchasedProducts;
        this.cart = cart;
        this.roles = roles;
    }

    public User() {
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void addRole(Roles role) {
        this.roles.add(role);
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<Purchase> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(Set<Purchase> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }
}
