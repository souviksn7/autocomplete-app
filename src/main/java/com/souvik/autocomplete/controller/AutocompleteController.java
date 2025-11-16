package com.souvik.autocomplete.controller;

import com.souvik.autocomplete.dto.AutocompleteRequest;
import com.souvik.autocomplete.dto.AutocompleteResponse;
import com.souvik.autocomplete.service.AutocompleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing the autocomplete API.
 */
@RestController
@RequestMapping("/api")
public class AutocompleteController {
    private final AutocompleteService autocompleteService;

    /**
     * Injects the autocomplete service.
     */
    public AutocompleteController(AutocompleteService autocompleteService) {
        this.autocompleteService = autocompleteService;
    }

    /**
     * Returns a list of names that start with the given prefix (case-insensitive).
     * @param prefix user-supplied text used for autocomplete
     * @return list of matching names or empty list if none found
     */
    @GetMapping("/autocomplete")
    public ResponseEntity<AutocompleteResponse> getAutocomplete(
            @RequestParam String prefix) {

        return ResponseEntity.ok(
                new AutocompleteResponse(autocompleteService.search(prefix))
        );
    }
}
