package com.dio.comics.service;

import com.dio.comics.dto.ComicsDTO;
import com.dio.comics.model.Comics;
import com.dio.comics.repository.ComicsRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ComicsService {
    private final ComicsRepository repository;
    private final ModelMapper mapper;

    public Flux<ComicsDTO> getAllComics() {
        return repository.findAll()
                .map(comics -> mapper.map(comics, ComicsDTO.class));
    }

    public Mono<ResponseEntity<ComicsDTO>> getComicsById(String id) {
       return repository
               .findById(id)
               .map(comics -> ResponseEntity.ok(mapper.map(comics, ComicsDTO.class)))
               .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<Comics> saveComics(ComicsDTO comicsDTO) {
        Comics comicsToSave = mapper.map(comicsDTO, Comics.class);
        return repository.save(comicsToSave);
    }

    public Mono<ResponseEntity<Comics>> updateComics(String id, ComicsDTO comicsDTO) {
        return repository.findById(id)
                .flatMap(comicsToUpdate -> {
                    mapper.map(comicsDTO, Comics.class);
                    return repository.save(comicsToUpdate);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<ResponseEntity<Void>> deleteComicsById(String id) {
        return repository.findById(id)
                .flatMap(comicsToDelete ->
                        repository.delete(comicsToDelete)
                        .then(Mono.just(ResponseEntity.ok().<Void>build()))
                ) .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<Void> deleteAllComics() {
        return repository.deleteAll();
    }
}
