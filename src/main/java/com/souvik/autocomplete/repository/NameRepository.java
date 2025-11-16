package com.souvik.autocomplete.repository;

import com.souvik.autocomplete.model.NameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing NameEntity records.
 * Extends JpaRepository to provide CRUD operations and query support.
 */
@Repository
public interface NameRepository extends JpaRepository<NameEntity, Long> {
}
