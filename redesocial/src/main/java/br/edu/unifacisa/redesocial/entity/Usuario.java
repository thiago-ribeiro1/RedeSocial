package br.edu.unifacisa.redesocial.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;


    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private Set<Post> postagens = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Post> getPostagens() {
        return postagens;
    }

    public void setPostagens(Set<Post> postagens) {
        this.postagens = postagens;
    }
}
