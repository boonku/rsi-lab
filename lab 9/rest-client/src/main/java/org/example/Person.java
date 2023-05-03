package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("_links")
public record Person(
        int id,
        String name,
        int age,
        String email
) {
}
