package br.edu.unifacisa.redesocial.repository;

import br.edu.unifacisa.redesocial.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsernameAndPassword(String username, String password);

}

