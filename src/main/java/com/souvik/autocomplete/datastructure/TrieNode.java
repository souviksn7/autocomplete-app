package com.souvik.autocomplete.datastructure;

import java.util.*;

/**
 * Represents a single node inside the Trie.
 * Each node stores:
 * -> A map of children for each next character.
 * -> A list of all names reachable below this node, enabling fast prefix lookup without traversing the whole Trie.
 */
 public class TrieNode {

    // Child character -> next TrieNode mapping
    private final Map<Character, TrieNode> children = new HashMap<>();

    // All names that share the prefix represented by this node
    private final List<String> wordsBelow = new ArrayList<>();

    /** Returns child nodes of this trie node. */
    Map<Character, TrieNode> getChildren(){
        return children;
    }

    /** Registers a word under this node for fast prefix lookup. */
    void addWord(String word){
        wordsBelow.add(word);
    }

    /**
     * Returns an unmodifiable view of all names stored under this Trie node.
     * Prevents external code from modifying the internal list.
     */
    List<String> getWordsBelow(){
        return Collections.unmodifiableList(wordsBelow);
    }
}
