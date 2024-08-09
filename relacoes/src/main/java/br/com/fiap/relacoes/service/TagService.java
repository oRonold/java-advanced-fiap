package br.com.fiap.relacoes.service;

import br.com.fiap.relacoes.model.blog.Post;
import br.com.fiap.relacoes.repository.PostRepository;
import br.com.fiap.relacoes.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostRepository postRepository;

    public Post adicionarTagAoPost(Long idPost, Long idTag){
        var post = postRepository.getReferenceById(idPost);
        var tag = tagRepository.getReferenceById(idTag);
        post.getTag().add(tag);
        tag.getPost().add(post);
        return post;
    }

}
