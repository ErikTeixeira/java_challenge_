package br.com.fiap.java_challenge.resource;

import br.com.fiap.java_challenge.dto.request.AvaliacaoRequest;
import br.com.fiap.java_challenge.dto.response.AvaliacaoResponse;
import br.com.fiap.java_challenge.entity.Avaliacao;
import br.com.fiap.java_challenge.entity.Estabelecimento;
import br.com.fiap.java_challenge.entity.TipoEstabelecimento;
import br.com.fiap.java_challenge.service.AvaliacaoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/avaliacao")
public class AvaliacaoResource implements ResourceDTO<Avaliacao, AvaliacaoRequest, AvaliacaoResponse> {

    @Autowired
    private AvaliacaoService avaliacaoService;


    @GetMapping
    public ResponseEntity<List<AvaliacaoResponse>> findAll(
            @RequestParam(name="comentario", required = false) String comentario,
            @RequestParam(name="nota", required = false) Long nota,
            @RequestParam(name="Data avaliacao", required = false) LocalDate dataAvaliacao,

            @RequestParam(name="ID estabelecimento", required = false) Long idEstab,
            @RequestParam(name="Nome estabelecimento", required = false) String nomeEstab,
            @RequestParam(name="Cep estabelecimento", required = false) String cepEstab,

            @RequestParam(name="Tipo estabelecimento", required = false) String tipoEstab
    ) {

        Estabelecimento estabelecimento = Estabelecimento.builder()
                .id(idEstab)
                .nome(nomeEstab)
                .cep(cepEstab)
                .tipo(TipoEstabelecimento.valueOf(tipoEstab))
                .build();

        Avaliacao avaliacao = Avaliacao.builder()
                .comentario(comentario)
                .nota(nota)
                .dataAvaliacao(dataAvaliacao)
                .estabelecimento(estabelecimento)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Avaliacao> example = Example.of(avaliacao, matcher);

        List<AvaliacaoResponse> list = avaliacaoService.findAll(example)
                .stream()
                .map(avaliacaoService::toResponse).toList();

        return ResponseEntity.ok(list);

    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<AvaliacaoResponse> findById(@PathVariable Long id) {

        var encontrado = avaliacaoService.findById( id );

        if (Objects.isNull( encontrado )) return ResponseEntity.notFound().build();

        var resposta = avaliacaoService.toResponse( encontrado );

        return ResponseEntity.ok( resposta );
    }


    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<AvaliacaoResponse> save(AvaliacaoRequest r) {

        Avaliacao entity = avaliacaoService.toEntity(r);
        Avaliacao saved = avaliacaoService.save(entity);
        AvaliacaoResponse response = avaliacaoService.toResponse(saved);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
