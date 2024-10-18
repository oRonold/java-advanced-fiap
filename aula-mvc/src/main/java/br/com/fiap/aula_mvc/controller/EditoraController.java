package br.com.fiap.aula_mvc.controller;

import br.com.fiap.aula_mvc.model.Editora;
import br.com.fiap.aula_mvc.model.Endereco;
import br.com.fiap.aula_mvc.repository.EditoraRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/editoras")
public class EditoraController {

    @Autowired
    private EditoraRepository editoraRepository;

    @GetMapping("cadastrar")
    public String cadastrar(Model model){
        model.addAttribute("editora", new Editora());
        return "biblioteca/cadastro-editora";
    }

    @PostMapping("cadastrar")
    @Transactional
    public String cadastro(@Valid Editora editora,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "biblioteca/cadastro-editora";
        }
        editoraRepository.save(editora);
        redirectAttributes.addFlashAttribute("msg", "Cadastrado com sucesso!");
        return "redirect:/editoras/cadastrar";
    }

    @GetMapping("detalhes/{id}")
    public String detalhes(@PathVariable Long id, Model model){
        Editora editora = editoraRepository.findById(id).orElseThrow(() -> new RuntimeException("Editora não encontrada"));
        model.addAttribute("editora", editora);
        return "biblioteca/editora-detalhes";
    }


}
