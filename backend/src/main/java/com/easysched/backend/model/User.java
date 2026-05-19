package com.easysched.backend.model;

import jakarta.persistence.*;

@Entity // Dice a JPA che questa classe deve essere una tabella
@Table(name = "users") // Specifica il nome della tabella su MySQL
public class User {

    @Id // Definisce la Chiave Primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID Auto-incrementale (1, 2, 3...)
    private Long id;

    @Column(nullable = false, length = 50) // Non può essere vuoto
    private String nome;

    @Column(unique = true, nullable = false) // L'email deve essere unica
    private String email;

    // --- COSTRUTTORI ---

    // Costruttore vuoto: FONDAMENTALE per Hibernate
    public User() {
    }

    // Costruttore utile per creare utenti velocemente
    public User(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // --- GETTER E SETTER ---
    // Servono a Spring per leggere e scrivere i dati nei campi privati

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}