package com.dio.comics.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReactiveEvent {
    private long id;
    private String type;
}
