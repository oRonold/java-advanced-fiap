package br.com.fiap.relacoes.service;

import br.com.fiap.relacoes.dto.CadastrarComentarioDTO;
import br.com.fiap.relacoes.model.Comentario;
import br.com.fiap.relacoes.repository.ComentarioRepository;
import br.com.fiap.relacoes.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PostRepository postRepository;

    public void cadastrar(CadastrarComentarioDTO dto, Long codigo){
        var comentario = new Comentario(dto);
        var post = postRepository.getReferenceById(codigo);
        comentario.setPost(post);
        comentarioRepository.save(comentario);
    }


}
