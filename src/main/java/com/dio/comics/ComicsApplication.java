package com.dio.comics;

import com.dio.comics.model.Comics;
import com.dio.comics.repository.ComicsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ComicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComicsApplication.class, args);
    }

    @Bean
    CommandLineRunner init (ComicsRepository repository) {
        return args -> {
            Flux<Comics> comicFlux = Flux.just(
                    new Comics(null, "The new 52", "Jim lee", "2011", "DC"),
                    new Comics(null, "Batman Knightfall", "Doug Moench", "1994", "DC"),
                    new Comics(null, "Extremis", "Warren Ellis", "2006", "Marvel"),
                    new Comics(null, "Deadpool kills the Marvel Universe", "Cullen Bunn", "2012", "Marvel"),
                    new Comics(null, "The Boys", "Garth Ennis", "2006", "Dynamite Entertainment")
            ).flatMap(repository::save);

            comicFlux
                    .thenMany(repository.findAll())
                    .subscribe(System.out::println);

        };
    }
}
