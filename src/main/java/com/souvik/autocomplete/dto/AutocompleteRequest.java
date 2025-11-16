package com.souvik.autocomplete.dto;

/** Request payload for autocomplete API.*/
public class AutocompleteRequest {

    private String prefix;

    public String getPrefix() { return prefix; }

    public void setPrefix(String prefix) { this.prefix = prefix; }
}
