package com.example.bookshop.author;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorResponse> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorResponse::from)
                .toList();
    }

    @Transactional
    public AuthorResponse create(AuthorRequest request) {
        AuthorEntity author = new AuthorEntity(request.name());

        return AuthorResponse.from(authorRepository.save(author));
    }
}
