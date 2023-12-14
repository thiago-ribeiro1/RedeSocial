package br.edu.unifacisa.redesocial.service;

import br.edu.unifacisa.redesocial.entity.Post;
import br.edu.unifacisa.redesocial.entity.Usuario;
import br.edu.unifacisa.redesocial.repository.PostRepository;
import br.edu.unifacisa.redesocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RedeSocialService {

    private final UsuarioRepository usuarioRepository;
    private final PostRepository postagemRepository;

    @Autowired
    public RedeSocialService(UsuarioRepository usuarioRepository, PostRepository postagemRepository) {
        this.usuarioRepository = usuarioRepository;
        this.postagemRepository = postagemRepository;
    }

    public Usuario findUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario criarConta(String username, String password) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setUsername(username);
        novoUsuario.setPassword(password);
        return usuarioRepository.save(novoUsuario);
    }

    public Usuario fazerLogin(String username, String password) {
        return usuarioRepository.findByUsernameAndPassword(username, password);
    }

    public void fazerPostagem(Usuario usuario, String texto) {
        Post postagem = new Post();
        postagem.setAutor(usuario);
        postagem.setTexto(texto);
        postagemRepository.save(postagem);
    }

    public List<Post> getAllPostagens() {
        Sort sortByDataDesc = Sort.by(Sort.Direction.DESC, "data");
        return postagemRepository.findAll(sortByDataDesc);
    }

    public List<Post> ListarPostagens() {
        List<Post> listPost = postagemRepository.findAll();
        Collections.reverse(listPost); // Inverte a lista para ordem decrescente
        return listPost;
    }

    public void fazerLogout() {

    }
}
