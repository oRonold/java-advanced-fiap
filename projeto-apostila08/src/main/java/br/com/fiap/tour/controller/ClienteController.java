package br.com.fiap.tour.controller;

import br.com.fiap.tour.domain.Cidade;
import br.com.fiap.tour.domain.Cliente;
import br.com.fiap.tour.dto.cidade.AtualizacaoCidadeDTO;
import br.com.fiap.tour.dto.cidade.CadastroCidadeDTO;
import br.com.fiap.tour.dto.cidade.DetalhesCidadeDTO;
import br.com.fiap.tour.dto.cliente.AtualizacaoClienteDTO;
import br.com.fiap.tour.dto.cliente.CadastroClienteDTO;
import br.com.fiap.tour.dto.cliente.DetalhesClienteDTO;
import br.com.fiap.tour.dto.pacote.DetalhesPacoteDTO;
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
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    //Criar o endpoint para pesquisar o cliente pela data de nascimento
    @GetMapping("por-data-nascimento")
    public ResponseEntity<Page<DetalhesClienteDTO>> buscar(@RequestParam("data") LocalDate data, Pageable page) {
        var lista = clienteRepository.buscarPorDataNascimento(data, page).map(DetalhesClienteDTO::new);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("por-nome")
    public ResponseEntity<Page<DetalhesClienteDTO>> parteNome(@RequestParam("nome") String nome, Pageable pageable){
        var clientes = clienteRepository.buscarPorNome(nome, pageable).map(DetalhesClienteDTO::new);
        return ResponseEntity.ok(clientes);
    }

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

    @GetMapping
    public ResponseEntity<Page<DetalhesClienteDTO>> listar(Pageable pageable){
        var page = clienteRepository.findAll(pageable).map(DetalhesClienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesClienteDTO> pesquisar(@PathVariable("id") Long id){
        var cliente = new DetalhesClienteDTO(clienteRepository.getReferenceById(id));
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/por-nome-cidade")
    public ResponseEntity<Page<DetalhesClienteDTO>> buscarPorEstado(@RequestParam("cidade") String cidade, Pageable pageable){
        var clientes = clienteRepository.buscarPorEstado(cidade, pageable).map(DetalhesClienteDTO::new);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/por-preco-maior")
    public ResponseEntity<Page<DetalhesClienteDTO>> pesquisarPorValorPacote(@RequestParam("valor") Double valor, Pageable pageable){
        var clientes = clienteRepository.buscarPorValorPacote(valor, pageable).map(DetalhesClienteDTO::new);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/por-cliente-cidade")
    public ResponseEntity<Page<DetalhesClienteDTO>> buscarPorNomeECidade(@RequestParam("cliente") String nome, @RequestParam("cidade") String cidade, Pageable pageable){
        var cliente = clienteRepository.buscarPorNomeClienteCidade(nome, cidade, pageable).map(DetalhesClienteDTO::new);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("por-lista-estados")
    public ResponseEntity<Page<DetalhesClienteDTO>> listarPorEstado(@RequestParam("estados") List<String> estados, Pageable pageable){
        var clientes = clienteRepository.listarPorEstado(estados, pageable).map(DetalhesClienteDTO::new);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/contar-clientes-estado")
    public ResponseEntity<Long> contarClientesEstado(@RequestParam("estado") String estado){
        var clientes = clienteRepository.contarQtdClienteEstado(estado);
        return ResponseEntity.ok().body(clientes);
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
