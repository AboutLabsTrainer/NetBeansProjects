package com.example.personmanagement.session;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Session-Bean für die Favoriten eines Browsers.
 * Jede HTTP-Session hat eigene IDs. Der Scoped Proxy lässt den Singleton-Controller die Bean trotzdem injizieren.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PersonFavorites {

    private final Set<Long> favoritePersonIds = new HashSet<>();

    public void addFavorite(Long personId) {
        favoritePersonIds.add(personId);
    }

    public void removeFavorite(Long personId) {
        favoritePersonIds.remove(personId);
    }

    public Set<Long> getFavoritePersonIds() {
        return Collections.unmodifiableSet(favoritePersonIds);
    }

    public boolean isFavorite(Long personId) {
        return favoritePersonIds.contains(personId);
    }
}

