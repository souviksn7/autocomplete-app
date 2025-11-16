package com.souvik.autocomplete.dto;

import java.util.List;

/** Response DTO for the autocomplete API.*/
public class AutocompleteResponse {

    private List<String> suggestions;

    public AutocompleteResponse(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    public List<String> getSuggestions() { return suggestions; }

    public void setSuggestions(List<String> suggestions) { this.suggestions = suggestions; }
}
