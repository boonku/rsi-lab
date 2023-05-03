package org.example;

public record ErrorResponse(
        String title,
        int status,
        String detail
) {
}
