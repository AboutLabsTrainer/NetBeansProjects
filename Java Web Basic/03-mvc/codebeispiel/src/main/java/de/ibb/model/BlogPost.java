package de.ibb.model;

import java.time.LocalDate;

/**
 * Model eines Blog-Eintrags für das MVC-Beispiel.
 */

public class BlogPost {
    private final long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDate date;

    public BlogPost(long id, String title, String content, String author, LocalDate date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
    public LocalDate getDate() { return date; }
}
