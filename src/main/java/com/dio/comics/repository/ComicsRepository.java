package com.dio.comics.repository;

import com.dio.comics.model.Comics;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ComicsRepository extends ReactiveMongoRepository<Comics, String> {
}
