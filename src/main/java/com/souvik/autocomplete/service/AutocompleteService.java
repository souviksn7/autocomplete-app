package com.souvik.autocomplete.service;

import java.util.List;

/**
 * Service interface for performing prefix-based autocomplete search.
 */
public interface AutocompleteService {

    /**
     * Searches for names matching the given prefix.
     *
     * @param prefix the text prefix to search for
     * @return list of matching names, or an empty list if no matches
     */
    List<String> search(String prefix);
}
