package com.dio.comics.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ComicsDTO {
    private String id;
    private String name;
    private String author;
    private String launchDate;
    private String publisher;
}
