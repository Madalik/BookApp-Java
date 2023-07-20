package com.example.bookAppbe.domain;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity(name= "users")
@Table(name= "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    Long id;
    @Column
    String name;
    @Column
    String email;
    @Column
    String username;
    @Column
    String password;

    @Column
    String role;

    @OneToMany(mappedBy = "user")
    Set<Book> books;


    public User(Long id, String name, String email, String username, String password, String role, Set<Book> books) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.books = books;
        this.password = password;
        this.role = role;
    }
    public User(){

    }

    public Long getUserId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        return Collections.singleton(authority);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
