package br.com.fiap.tour.controller;

import br.com.fiap.tour.domain.Cidade;
import br.com.fiap.tour.domain.Cliente;
import br.com.fiap.tour.dto.cidade.AtualizacaoCidadeDTO;
import br.com.fiap.tour.dto.cidade.CadastroCidadeDTO;
import br.com.fiap.tour.dto.cidade.DetalhesCidadeDTO;
import br.com.fiap.tour.dto.cliente.AtualizacaoClienteDTO;
import br.com.fiap.tour.dto.cliente.CadastroClienteDTO;
import br.com.fiap.tour.dto.cliente.DetalhesClienteDTO;
import br.com.fiap.tour.repository.CidadeRepository;
import br.com.fiap.tour.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesClienteDTO> cadastrar(@RequestBody @Valid CadastroClienteDTO dto, UriComponentsBuilder builder) {
        var cliente = new Cliente(dto);
        var cidade = cidadeRepository.getReferenceById(dto.endereco().cidadeId());
        cliente.getEndereco().setCidade(cidade);
        cliente = clienteRepository.save(cliente);
        var uri = builder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesClienteDTO(cliente));
    }

    @GetMapping("data_nascimento")
    public ResponseEntity<Page<DetalhesClienteDTO>> pesquisarPelaDataNascimento(@RequestParam("data") LocalDate data, Pageable pageable){
        var lista = clienteRepository.buscarPorDataNascimento(data, pageable).map(DetalhesClienteDTO::new);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("parte-nome")
    public ResponseEntity<Page<DetalhesClienteDTO>> pesquisarPorParteDoNome(@RequestParam("nome") String nome, Pageable pageable){
        var lista = clienteRepository.buscarPorParteDoNome(nome, pageable).map(DetalhesClienteDTO::new);
        return ResponseEntity.ok(lista);
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesClienteDTO>> pesquisar(Pageable pageable){
        var page = clienteRepository.findAll(pageable).map(DetalhesClienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesClienteDTO> pesquisar(@PathVariable("id") Long id){
        var cliente = new DetalhesClienteDTO(clienteRepository.getReferenceById(id));
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesClienteDTO> atualizar(@PathVariable("id") Long id, @RequestBody @Valid AtualizacaoClienteDTO dto){
        var cliente = clienteRepository.getReferenceById(id);
        cliente.atualizar(dto);
        return ResponseEntity.ok(new DetalhesClienteDTO(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable("id") Long id){
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
