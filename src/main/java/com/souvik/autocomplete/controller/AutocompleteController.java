package com.souvik.autocomplete.controller;

import com.souvik.autocomplete.dto.AutocompleteRequest;
import com.souvik.autocomplete.dto.AutocompleteResponse;
import com.souvik.autocomplete.exception.ErrorDetails;
import com.souvik.autocomplete.service.AutocompleteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing the autocomplete API.
 */
@Tag(name = "Autocomplete API", description = "Prefix-based name suggestions")
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
    @Operation(
            summary = "get autocomplete suggestions",
            description = "Returns list of names matching the provided prefix"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AutocompleteResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid prefix provided",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    )
            }
    )
    @GetMapping("/autocomplete")
    public ResponseEntity<AutocompleteResponse> getAutocomplete(
            @Parameter(description = "Prefix to search for")
            @RequestParam String prefix) {

        return ResponseEntity.ok(
                new AutocompleteResponse(autocompleteService.search(prefix))
        );
    }
}
