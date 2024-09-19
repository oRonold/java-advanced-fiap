package br.com.fiap.aula_mvc.controller;

import br.com.fiap.aula_mvc.model.Livro;
import br.com.fiap.aula_mvc.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @GetMapping("cadastrar")
    public String cadastro(Livro livro){
        return "biblioteca/cadastro";
    }

    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@ModelAttribute Livro livro, Model model){
        repository.save(livro);
        model.addAttribute("msg", "Livro cadastrado!");
        return "biblioteca/cadastro";
    }

}
