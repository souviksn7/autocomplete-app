package com.souvik.autocomplete.service.impl;

import com.souvik.autocomplete.datastructure.Trie;
import com.souvik.autocomplete.model.NameEntity;
import com.souvik.autocomplete.repository.NameRepository;
import com.souvik.autocomplete.service.AutocompleteService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Service implementation that loads all names from the database into an in-memory Trie
 * and performs fast O(K) prefix-based autocomplete searches.
 */
@Service
public class AutocompleteServiceImpl implements AutocompleteService {

    // Repository used to fetch all names from the H2 database
    private final NameRepository nameRepository;

    // In-memory Trie used for O(K) prefix lookup
    private final Trie trie = new Trie();

    public AutocompleteServiceImpl(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    /**
     * Loads all names from the database into the Trie once at application startup.
     */
    @PostConstruct
    public void init() {
        List<NameEntity> all = nameRepository.findAll();
        all.forEach(n -> trie.insert(n.getName()));
        System.out.println("Trie built with " + all.size() + " names.");
    }

    /**
     * Performs an autocomplete search.
     * If the prefix is null or empty, an empty list is returned.
     *
     * @param prefix the text prefix to search for
     * @return list of matching names, or an empty list if no matches
     */
    @Override
    public List<String> search(String prefix) {

        // If prefix is null or empty then return empty list
        if (prefix == null || prefix.trim().isEmpty()) {
            return Collections.emptyList();
        }

        return trie.searchByPrefix(prefix);
    }
}
