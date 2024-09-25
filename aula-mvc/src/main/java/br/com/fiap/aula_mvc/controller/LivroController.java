package br.com.fiap.aula_mvc.controller;

import br.com.fiap.aula_mvc.model.Livro;
import br.com.fiap.aula_mvc.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @GetMapping("cadastrar")
    public String cadastro(Livro livro){
        return "biblioteca/formulario";
    }

    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@ModelAttribute Livro livro, Model model){
        repository.save(livro);
        model.addAttribute("msg", "Livro cadastrado!");
        return "biblioteca/formulario";
    }

    @GetMapping("listar")
    public String listar(Model model){
        model.addAttribute("livros", repository.findAll());
        return "biblioteca/lista";
    }

    @GetMapping("/editar/{id}")
    public String exibirEditar(@PathVariable("id") Long codigo, Model model){
        model.addAttribute("livro", repository.findById(codigo));
        return "biblioteca/editar";
    }

    @PostMapping("editar")
    @Transactional
    public String editar(Livro livro, Model model){
        repository.save(livro);
        model.addAttribute("msg", "Livro editado!");
        model.addAttribute("livros", repository.findAll());
        return "biblioteca/lista";
    }

    @PostMapping("excluir")
    @Transactional
    public String excluir(@RequestParam("livroId") Long id, Model model){
        repository.deleteById(id);
        model.addAttribute("msg", "Livro excluído!");
        return "biblioteca/lista";
    }

}
