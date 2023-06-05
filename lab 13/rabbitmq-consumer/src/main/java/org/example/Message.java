package org.example;

import java.time.LocalDateTime;

public record Message(
        LocalDateTime time,
        String message,
        Integer counter
) {
}
