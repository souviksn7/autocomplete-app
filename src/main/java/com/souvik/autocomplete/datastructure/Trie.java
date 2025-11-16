package com.souvik.autocomplete.datastructure;

import java.util.*;

/**
 * Trie implementation optimized for prefix-based autocomplete.
 * Each node keeps a list of all original names reachable from that node,
 * allowing O(K) prefix search where K is the length of the prefix.
 */
public class Trie {

    // Root node of the Trie (represents empty prefix)
    private final TrieNode root = new TrieNode();

    /**
     * Insert a name into the Trie.
     * Stores lowercase characters for traversal but keeps original case
     * in wordsBelow so we can return names exactly as provided.
     */
    public void insert(String originalName){
        if(originalName == null) return;

        String key = originalName.toLowerCase(Locale.ROOT);
        TrieNode node = root;

        // Store the full word at the root
        node.addWord(originalName);

        for(char c : key.toCharArray()){
            node = node.getChildren().computeIfAbsent(c, ch -> new TrieNode());
            node.addWord(originalName); // Add word to this node's subtree
        }
    }

    /**
     * Search for names matching the given prefix in O(K) time
     * where K = length of the prefix.
     * Returns all names stored below the last matched node.
     */
    public List<String> searchByPrefix(String prefix){
        if(prefix == null) return Collections.emptyList();

        String key = prefix.toLowerCase(Locale.ROOT);
        TrieNode node = root;

        for (char c : key.toCharArray()) {
            node = node.getChildren().get(c);

            // Prefix does not exist
            if (node == null) return Collections.emptyList();
        }

        return node.getWordsBelow();
    }
}
