package br.com.fiap.store.aula04.controller;

import br.com.fiap.store.aula04.dto.AtualizarClienteDTO;
import br.com.fiap.store.aula04.dto.CadastroClienteDTO;
import br.com.fiap.store.aula04.dto.DadosCadatroClienteDTO;
import br.com.fiap.store.aula04.dto.DetalhamentoClienteDTO;
import br.com.fiap.store.aula04.model.Cliente;
import br.com.fiap.store.aula04.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosCadatroClienteDTO> cadastro(@RequestBody @Valid CadastroClienteDTO dto, UriComponentsBuilder builder){
        var cliente = new Cliente(dto);
        repository.save(cliente);
        var url = builder.path("/cliente/{id}").buildAndExpand(cliente.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new DadosCadatroClienteDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<List<DetalhamentoClienteDTO>> listarClientes(Pageable paginacao){
        var page = repository.findAll(paginacao).stream().map(DetalhamentoClienteDTO::new).toList();
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoClienteDTO> pesquisarPorId(@PathVariable Long id){
        var cliente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoClienteDTO(cliente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhamentoClienteDTO> atualizar(@RequestBody AtualizarClienteDTO dto){
        var cliente = repository.getReferenceById(dto.id());
        cliente.atualizarInformacoes(dto);
        return ResponseEntity.ok(new DetalhamentoClienteDTO(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var cliente = repository.getReferenceById(id);
        repository.delete(cliente);
        return ResponseEntity.noContent().build();
    }

}
