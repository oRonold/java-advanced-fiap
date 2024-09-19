package br.com.fiap.aula_mvc.controller;

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
    public String cadastro(String nome, Double preco, Model model){
        model.addAttribute("nome", nome);
        model.addAttribute("preco", preco);
        model.addAttribute("msg", "Produto cadastrado com sucesso!" + nome + " " + preco);
        return "produto/cadastro";
    }
}
