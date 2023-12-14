package br.edu.unifacisa.redesocial.repository;

import br.edu.unifacisa.redesocial.entity.Post;
import br.edu.unifacisa.redesocial.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Set<Post> findByAutor(Usuario usuario);

}
