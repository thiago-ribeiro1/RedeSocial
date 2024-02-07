package br.edu.unifacisa.redesocial.controller;

import br.edu.unifacisa.redesocial.entity.Post;
import br.edu.unifacisa.redesocial.entity.Usuario;
import br.edu.unifacisa.redesocial.service.RedeSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@org.springframework.stereotype.Controller
public class RedeSocialController {

    private RedeSocialService redeSocialService;

    @Autowired
    public void Controller(RedeSocialService redeSocialService) {
        this.redeSocialService = redeSocialService;
    }

    @GetMapping("/")
    public String redirectToContext() {
        return "redirect:/redesocial";
    }

    @GetMapping("/redesocial") // Acessar em localhost:8080/redesocial
    public String index() {
        return "redesocial";
    }

    @PostMapping("/login")
    public String criarConta(@RequestParam String username, @RequestParam String password, Model model) {
        redeSocialService.criarConta(username, password);
        model.addAttribute("mensagem", "Conta criada com sucesso!");
        return "login";
    }

    @PostMapping("/home-page")
    public String fazerLogin(@RequestParam String username, @RequestParam String password, Model model) {
        Usuario usuarioLogado = redeSocialService.fazerLogin(username, password);
        if (usuarioLogado != null) { // Verifica se o usuário foi autenticado
            model.addAttribute("mensagem", "Login bem-sucedido!");
            model.addAttribute("usuarioLogado", usuarioLogado);
            return "home";
        } else { // Caso o usuário não seja autenticado, redireciona de volta para a página de login
            model.addAttribute("mensagem", "Usuário ou senha incorretos. Tente novamente.");
            return "login";
        }
    }

    @PostMapping("/timeline")
    public String fazerPostagem(@RequestParam String texto, @RequestParam Long usuarioLogadoId, Model model) {
        Usuario usuarioLogado = redeSocialService.findUsuarioById(usuarioLogadoId);
        redeSocialService.fazerPostagem(usuarioLogado, texto);

        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = dataAtual.format(formatter);

        String mensagem = "Nova postagem de " + usuarioLogado.getUsername() + ": " + texto + "<br>Postado em " + dataFormatada;

        model.addAttribute("mensagem", mensagem);
        model.addAttribute("postagens", redeSocialService.getAllPostagens());

        return "post";
    }

    @GetMapping("/redesocial/postagens")
    public String listarPostagens(Model model) {
        List<Post> postagens = redeSocialService.ListarPostagens();
        model.addAttribute("postagens", postagens);
        return "postagens";
    }

    @PostMapping("/redesocial/logout")
    public String fazerLogout() {
        redeSocialService.fazerLogout();
        return "redesocial";
    }

}
