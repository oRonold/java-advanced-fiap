package br.com.fiap.relacoes.service;

import br.com.fiap.relacoes.dto.CadastrarComentarioDTO;
import br.com.fiap.relacoes.model.blog.Comentario;
import br.com.fiap.relacoes.repository.ComentarioRepository;
import br.com.fiap.relacoes.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public Comentario adicionarComentario(Long postId, CadastrarComentarioDTO dto){
        var post = postRepository.getReferenceById(postId);
        var comentario = new Comentario(dto);
        comentario.setPost(post);
        post.getComentario().add(comentario);
        comentarioRepository.save(comentario);
        postRepository.save(post);
        return comentario;
    }

}
