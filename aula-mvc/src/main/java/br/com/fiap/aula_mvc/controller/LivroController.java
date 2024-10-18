package br.com.fiap.aula_mvc.controller;

import br.com.fiap.aula_mvc.model.Genero;
import br.com.fiap.aula_mvc.model.Livro;
import br.com.fiap.aula_mvc.repository.EditoraRepository;
import br.com.fiap.aula_mvc.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private EditoraRepository editoraRepository;

    @GetMapping("cadastrar")
    public String cadastro(Livro livro, Model model){
        model.addAttribute("generos", Genero.values());
        model.addAttribute("editoras", editoraRepository.findAll());
        return "biblioteca/formulario";
    }

    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Livro livro,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("generos", Genero.values());
            return "biblioteca/formulario";
        }
        repository.save(livro);
        redirectAttributes.addFlashAttribute("msg", "Livro Cadastrado!");
        return "redirect:/livros/listar";
    }

    @GetMapping("pesquisar")
    public String pesquisar(@RequestParam String query, Model model){
        model.addAttribute("livros", repository.findByTituloContainingIgnoreCase(query));
        return "biblioteca/lista";
    }

    @GetMapping("listar")
    public String listar(Model model){
        model.addAttribute("livros", repository.findAll());
        return "biblioteca/lista";
    }

    @GetMapping("/editar/{id}")
    public String exibirEditar(@PathVariable("id") Long codigo, Model model){
        model.addAttribute("livro", repository.findById(codigo));
        model.addAttribute("editoras", editoraRepository.findAll());
        model.addAttribute("generos", Genero.values());
        return "biblioteca/editar";
    }

    @PostMapping("editar")
    @Transactional
    public String editar(@Valid Livro livro, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("generos", Genero.values());
            return "biblioteca/editar";
        }
        repository.save(livro);
        redirectAttributes.addFlashAttribute("msg", "Livro Editado!");
        return "redirect:/livros/listar";
    }

    @PostMapping("excluir")
    @Transactional
    public String excluir(@RequestParam("livroId") Long id, RedirectAttributes redirectAttributes){
        repository.deleteById(id);
        redirectAttributes.addFlashAttribute("msg","Livro Excluido");
        return "redirect:/livros/listar";
    }

}
