package br.com.fiap.aula_mvc.controller;

import br.com.fiap.aula_mvc.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("produto")
public class FormularioController {

    @GetMapping("cadastro")
    public String cadastrar(){
        return "produto/cadastro";
    }

    @PostMapping("cadastro")
    public String cadastro(Produto produto, Model model){
        model.addAttribute("prod", produto);
        model.addAttribute("msg", "Produto cadastrado com sucesso!");
        return "produto/sucesso";
    }
}
