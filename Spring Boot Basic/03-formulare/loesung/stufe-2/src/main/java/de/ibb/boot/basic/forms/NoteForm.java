package de.ibb.boot.basic.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class NoteForm {
    @NotBlank
    private String title;

    @NotBlank
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
