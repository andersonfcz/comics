package com.dio.comics.controller;

import com.dio.comics.dto.ComicsDTO;
import com.dio.comics.model.Comics;
import com.dio.comics.model.ReactiveEvent;
import com.dio.comics.service.ComicsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/v1/comics")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ComicsController {
    private final ComicsService service;

    @GetMapping
    public Flux<ComicsDTO> getAllComics() {
        return service.getAllComics();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<ComicsDTO>> getComicsById(@PathVariable(value = "id") String id) {
        return service.getComicsById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Comics> saveComics(@RequestBody ComicsDTO comicsDTO) {
        return service.saveComics(comicsDTO);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Comics>> updateComics(@PathVariable(value = "id") String id, @RequestBody ComicsDTO comicsDTO) {
        return service.updateComics(id, comicsDTO);
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteComicsById(@PathVariable(value = "id") String id) {
        return service.deleteComicsById(id);
    }

    @DeleteMapping
    public Mono<Void> deleteAllComics() {
        return service.deleteAllComics();
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ReactiveEvent> getReactiveEvents() {
        return Flux.interval(Duration.ofSeconds(3))
                .map(val -> new ReactiveEvent(val, "Event"));
    }

}
