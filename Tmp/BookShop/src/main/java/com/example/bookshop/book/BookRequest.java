package com.example.bookshop.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record BookRequest(

        @NotBlank(message = "Der Titel darf nicht leer sein.")
        @Size(
                max = 200,
                message = "Der Titel darf höchstens 200 Zeichen enthalten."
        )
        String title,

        @NotNull(message = "Die Autor-ID ist erforderlich.")
        @Positive(message = "Die Autor-ID muss größer als 0 sein.")
        Long authorId,

        @NotBlank(message = "Die ISBN darf nicht leer sein.")
        @Pattern(
                regexp = "^[0-9-]{10,17}$",
                message = "Die ISBN hat kein gültiges Format."
        )
        String isbn
) {
}
