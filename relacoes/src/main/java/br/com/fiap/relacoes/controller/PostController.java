package br.com.fiap.relacoes.controller;

import br.com.fiap.relacoes.dto.*;
import br.com.fiap.relacoes.model.blog.DetalhesPost;
import br.com.fiap.relacoes.model.blog.Post;
import br.com.fiap.relacoes.repository.PostRepository;
import br.com.fiap.relacoes.repository.TagRepository;
import br.com.fiap.relacoes.service.ComentarioService;
import br.com.fiap.relacoes.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/posts")
@Tag(name = "Post", description = "Operações relacionadas com Post do blog")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ComentarioService service;

    @Autowired
    private TagService tagService;

    @PostMapping
    @Transactional
    @Operation(summary = "Cria um novo Post", description = "Operação para cadastrar novos posts no Blog")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastro com sucesso", content = @Content(schema = @Schema(implementation = DetalhesPost.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<DetalhesPostDTO> criar(@RequestBody @Valid CadastrarPostDTO dto, UriComponentsBuilder builder){
        var post = new Post(dto);
        postRepository.save(post);
        var uri = builder.path("/posts/{id}").buildAndExpand(post.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPostDTO(post));
    }

    @PostMapping("/{id}/comentarios")
    @Transactional
    @Operation(summary = "Adiciona um comentário", description = "Adiciona um novo comentário a um Post específico")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastro com sucesso", content = @Content(schema = @Schema(implementation = DetalhesComentariosDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<DetalhesComentariosDTO> adicionarComentario(@PathVariable Long id, @RequestBody @Valid CadastrarComentarioDTO dto, UriComponentsBuilder builder){
        var comentario = service.adicionarComentario(id, dto);
        var uri = builder.path("/{id}").buildAndExpand(comentario.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesComentariosDTO(comentario));
    }

    @GetMapping
    @Operation(summary = "Lista os Posts", description = "Retorna todos os Posts criados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pesquisa realizada com sucesso", content = @Content(schema = @Schema(implementation = DetalhesPostDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content)
    })
    public ResponseEntity<Page<DetalhesPostDTO>> listar(Pageable pageable){
        var posts = postRepository.findAll(pageable).map(DetalhesPostDTO::new);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um Post pelo Id", description = "Informa o Id do Post e ele é exibido se existir")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pesquisa realizada com sucesso", content = @Content(schema = @Schema(implementation = DetalhesPostDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Post não encontrado", content = @Content)
    })
    public ResponseEntity<DetalhesPostDTO> buscarPorId(@PathVariable Long id){
        var post = postRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesPostDTO(post));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualiza um Post", description = "Atualiza as informações do Post entrando com o Id do mesmo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualização feita com sucesso", content = @Content(schema = @Schema(implementation = DetalhesPostDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Post não encontrado", content = @Content)
    })
    public ResponseEntity<DetalhesPostDTO> atualizar(@RequestBody @Valid AtualizarPostDTO dto, @PathVariable Long id){
        var post = postRepository.getReferenceById(id);
        post.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesPostDTO(post));
    }

    @PutMapping("/{post}/tags/{tag}")
    @Transactional
    @Operation(summary = "Adiciona uma Tag", description = "Adiciona uma Tag ao Post indicado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tag adicionada com sucesso", content = @Content(schema = @Schema(implementation = DetalhesTagPostDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Post ou Tag não encontrados", content = @Content)
    })
    public ResponseEntity<DetalhesTagPostDTO> adicionarTag(@PathVariable("post") Long postId, @PathVariable("tag") Long tagId){
        var post = tagService.adicionarTagAoPost(postId, tagId);
        return ResponseEntity.ok().body(new DetalhesTagPostDTO(post));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Exclui um Post", description = "Exclui um Post relacionado com o Id passado, exclui também os comentários")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var post = postRepository.getReferenceById(id);
        postRepository.delete(post);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idPost}/tags/{idTag}")
    @Transactional
    @Operation(summary = "Remove Tag", description = "Remove uma Tag relacionada com o Post")
    public ResponseEntity<Void> removerTag(@PathVariable("idPost") Long idPost, @PathVariable("idTag") Long idTag){
        var post = postRepository.getReferenceById(idPost);
        var tag = tagRepository.getReferenceById(idTag);
        post.getTag().remove(tag);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}/tags")
    @Transactional
    @Operation(summary = "Remove todas Tags", description = "Remove todas as Tags do Post")
    public ResponseEntity<Void> removerTodasTags(@PathVariable Long id){
        var post = postRepository.getReferenceById(id);
        post.getTag().clear();
        return ResponseEntity.noContent().build();
    }

}
