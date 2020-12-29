package com.dio.comics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comics {
    @Id
    private String id;
    private String name;
    private String author;
    private String launchDate;
    private String publisher;

}
