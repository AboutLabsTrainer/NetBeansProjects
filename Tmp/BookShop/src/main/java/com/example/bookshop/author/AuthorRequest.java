package com.example.bookshop.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorRequest(

        @NotBlank(message = "Der Name darf nicht leer sein.")
        @Size(
                max = 150,
                message = "Der Name darf höchstens 150 Zeichen enthalten."
        )
        String name
) {
}
