package br.com.fiap.aula_mvc.controller;

import br.com.fiap.aula_mvc.model.Disciplina;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("calculadora")
public class MediaController {

    @GetMapping("media")
    public String calcular(){
        return "calculadora/media";
    }

    @PostMapping("media")
    public String media(Disciplina disciplina, Model model){
        model.addAttribute("disciplina", disciplina.calculadrMediaAnual());
        return "calculadora/media";
    }
}
